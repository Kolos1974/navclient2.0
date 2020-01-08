package data.dao.szamla;

import config.Config;
import data.dao.BaseDao;
import data.entity.Szamla;
import data.entity.SzamlaTetel;
import data.entity.VeSza;
import exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SzamlaDao extends BaseDao {

    public List<Szamla> getAll() throws DatabaseException {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT TOP 10 * FROM " + getTableName() +
                             " INNER JOIN Ve_sza ON Ve_sza.VE_SZAKOD = " + getTableName() + ".VE_SZAKOD " +
                             "WHERE (" + getTableName() + ".STATUS IS NULL OR "
                             + getTableName() + ".STATUS = '')" +
                             "AND " + getTableName() + ".LEZART = 1 " +
                             "AND " + getTableName() + ".SZDAT >= ? " +
                             "AND abs(" + getTableName() + ".AFA_OSSZEG) >= ? " +
                             "AND Ve_sza.OrszagKod = 'HU' " +
                             "AND (Ve_sza.ADOSZAM >= '1' AND Ve_sza.ADOSZAM <= '9')"
             )) {
            pstmt.setTimestamp(1, Config.navInvoiceDate);
            pstmt.setInt(2, Config.navMinTax);
            try(ResultSet szamlaResultSet = pstmt.executeQuery()){
                List<Szamla> szamlak = new ArrayList<>();
                while (szamlaResultSet.next()) {
                    Szamla szamla = getSzamlaFromResultSet(szamlaResultSet);
                    szamla.setVeSza(getVeszaFromResultSet(szamlaResultSet));

                    PreparedStatement preparedStatementSajatBank = conn.prepareStatement(
                            "SELECT SzamlaSzam FROM Sajat_Bank WHERE Azonosito = ?");
                    preparedStatementSajatBank.setInt(1, szamlaResultSet.getInt("S_BANK_AZONOSITO"));
                    ResultSet sajatBankResultSet = preparedStatementSajatBank.executeQuery();
                    if (sajatBankResultSet.next()) szamla.setSzamlaSzam(sajatBankResultSet.getString("SzamlaSzam"));

                    PreparedStatement preparedStatementTetel = conn.prepareStatement(
                            "SELECT * FROM " + getTetelTableName()
                                    + " INNER JOIN Cikkek ON Cikkek.Cikkszam = " + getTetelTableName() + ".Cikkszam"
                                    + " WHERE IKTSZAM = ? ORDER BY TETELSORSZ");
                    if (szamla.getType() == Szamla.SzamlaType.V)
                        preparedStatementTetel.setInt(1, Integer.parseInt(szamla.getIktSzam()));
                    else preparedStatementTetel.setString(1, szamla.getIktSzam());
                    ResultSet tetelResultSet = preparedStatementTetel.executeQuery();
                    while (tetelResultSet.next()) {
                        szamla.addTetel(getSzamlaTetelFromResultSet(tetelResultSet));
                    }
                    szamla.calculateSummeries();
                    szamlak.add(szamla);
                }
                return szamlak;
            }
        } catch (SQLException e) {
            throw new DatabaseException(getTableName() + ": " + e.getMessage());
        }
    }

    public boolean updateStatusz(String id, String status) throws DatabaseException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE " + getTableName() + " SET STATUS = ? WHERE IKTSZAM = ?")
        ) {
            preparedStatement.setString(1, status);
            if (getType() == Szamla.SzamlaType.V)
                preparedStatement.setInt(2, Integer.parseInt(id));
            else preparedStatement.setString(2, id);
            return preparedStatement.execute();
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException(getTableName() + ": " + e.getMessage());
        }
    }

    private Szamla getSzamlaFromResultSet(ResultSet rs) throws SQLException {
        Szamla szamla = new Szamla();
        szamla.setType(getType());
        if (getType() == Szamla.SzamlaType.V) {
            szamla.setIktSzam(Integer.toString(rs.getInt("IKTSZAM")).trim());
            szamla.setStEredeti(Integer.toString(rs.getInt("ST_Eredeti")).trim());
        } else {
            szamla.setIktSzam(rs.getString("IKTSZAM").trim());
            String stEredeti = rs.getString("ST_Eredeti");
            szamla.setStEredeti(stEredeti != null ? stEredeti.trim() : null);
            szamla.setSzidoszTol(rs.getTimestamp("SZIDOSZTOL"));
            szamla.setSzidoszIg(rs.getTimestamp("SZIDOSZIG"));
        }
        szamla.setSzekod(rs.getString("SZEKOD"));
        szamla.setSzdat(rs.getTimestamp("SZDAT"));
        szamla.setFizmodkod(rs.getString("FIZMODKOD"));
        szamla.setEsdat(rs.getTimestamp("ESDAT"));
        szamla.setTeljdat(rs.getTimestamp("TELJDAT"));
        szamla.setBekdat(rs.getTimestamp("BEKDAT"));
        return szamla;
    }

    private VeSza getVeszaFromResultSet(ResultSet rs) throws SQLException {
        VeSza veSza = new VeSza();
        veSza.setMegnev(rs.getString("MEGNEV"));
        veSza.setVeSzakod(rs.getString("VE_SZAKOD"));
        veSza.setAdoszam(rs.getString("ADOSZAM"));
        veSza.setIrsz(rs.getString("IRSZ"));
        veSza.setHelyseg(rs.getString("HELYSEG"));
        veSza.setUtca(rs.getString("UTCA"));
        veSza.setKozterulet(rs.getString("KOZTERULET"));
        veSza.setHazszam(rs.getString("HAZSZAM"));
        return veSza;
    }

    private SzamlaTetel getSzamlaTetelFromResultSet(ResultSet rs) throws SQLException {
        SzamlaTetel tetel = new SzamlaTetel();
        tetel.setTetelsorsz(rs.getInt("TETELSORSZ"));
        tetel.setMegnev(rs.getString("MEGNEV"));
        tetel.setMennyiseg(rs.getBigDecimal("MENNYISEG"));
        tetel.setMe(rs.getString("ME"));
        tetel.setEgysegAr(rs.getBigDecimal("EGYSEGAR"));
        tetel.setAfaalap(rs.getBigDecimal("AFAALAP"));
        tetel.setAfaSzazalek(rs.getBigDecimal("AFA_SZAZALEK"));
        tetel.setAfaertek(rs.getBigDecimal("AFAERTEK"));
        tetel.setBrutto(rs.getBigDecimal("BRUTTO"));
        tetel.setIktSzam(rs.getString("IKTSZAM"));
        tetel.setKozvSzolg(rs.getString("KOZVSZOLG"));
        return tetel;
    }

    private String getTetelTableName() {
        return getTableName() + "T";
    }

    protected abstract Szamla.SzamlaType getType();

}
