package data.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Szamla {

    public enum SzamlaType {
        B, V, K, G, P, Z, O
    }

    public enum States {
        PROCESSING,
        DONE,
        ABORTED,
        RECEIVED
    }

    private SzamlaType type;
    private String iktSzam;
    private String stEredeti;
    private String szekod;
    //NOTE: most konstans
    private String category = "NORMAL";
    private Timestamp szdat;
    //NOTE: most konstans
    private String currencyCode = "HUF";
    private BigDecimal exchRate = new BigDecimal(1);
	private String fizmodkod;
    private Timestamp esdat;
    //NOTE: most konstans
    private String appearance = "PAPER";
    private Timestamp teljdat;
    private Timestamp bekdat;
    private Timestamp szidoszTol;
    private Timestamp szidoszIg;

    //OTHER TABLE
    private List<SzamlaTetel> tetels = new ArrayList<>();
    private VeSza veSza;
    private String szamlaSzam;

    //SUMMARY
    private Map<BigDecimal, VatSummary> vatSummeries;
    private OverallSummary overallSummary;

    public Map<BigDecimal, VatSummary> getVatSummeries() {
        return vatSummeries;
    }

    public void setVatSummeries(Map<BigDecimal, VatSummary> vatSummeries) {
        this.vatSummeries = vatSummeries;
    }

    public Timestamp getSzidoszTol() {
        return szidoszTol;
    }

    public void setSzidoszTol(Timestamp szidoszTol) {
        this.szidoszTol = szidoszTol;
    }

    public Timestamp getSzidoszIg() {
        return szidoszIg;
    }

    public void setSzidoszIg(Timestamp szidoszIg) {
        this.szidoszIg = szidoszIg;
    }

    public String getStEredeti() {
        return stEredeti;
    }

    public void setStEredeti(String stEredeti) {
        this.stEredeti = stEredeti;
    }

    public Timestamp getBekdat() {
        return bekdat;
    }

    public void setBekdat(Timestamp bekdat) {
        this.bekdat = bekdat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getSzdat() {
        return szdat;
    }

    public void setSzdat(Timestamp szdat) {
        this.szdat = szdat;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFizmodkod() {
        return fizmodkod;
    }

    public void setFizmodkod(String fizmodkod) {
        this.fizmodkod = fizmodkod;
    }

    public Timestamp getEsdat() {
        return esdat;
    }

    public Timestamp getTeljdat() {
        return teljdat;
    }

    public void setTeljdat(Timestamp teljdat) {
        this.teljdat = teljdat;
    }

    public String getSzamlaSzam() {
        return szamlaSzam;
    }

    public void setSzamlaSzam(String szamlaSzam) {
        this.szamlaSzam = szamlaSzam;
    }

    public void setEsdat(Timestamp esdat) {
        this.esdat = esdat;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public VeSza getVeSza() {
        return veSza;
    }

    public void setVeSza(VeSza veSza) {
        this.veSza = veSza;
    }

    public SzamlaType getType() {
        return type;
    }

    public void setType(SzamlaType type) {
        this.type = type;
    }

    public String getIktSzam() {
        return iktSzam;
    }

    public void setIktSzam(String iktSzam) {
        this.iktSzam = iktSzam;
    }

    public String getSzekod() {
        return szekod;
    }

    public void setSzekod(String szekod) {
        this.szekod = szekod;
    }

    public void addTetel(SzamlaTetel szamlaTetel) {
        tetels.add(szamlaTetel);
    }

    public List<SzamlaTetel> getTetels() {
        return tetels;
    }

    public OverallSummary getOverallSummary() {
        return overallSummary;
    }
        
    public BigDecimal getExchRate() {
		return exchRate;
	}

	public void setExchRate(BigDecimal exchRate) {
		this.exchRate = exchRate;
	}

    public void calculateSummeries() {
        vatSummeries = new HashMap<>();
        overallSummary = new OverallSummary();
        for (SzamlaTetel tetel : tetels) {
            //VatSummary
            VatSummary vatSummary = vatSummeries.get(tetel.getAfaSzazalek());
            if (vatSummary == null) {
                vatSummary = new VatSummary(tetel.getAfaSzazalek());
            }
            BigDecimal afaalap = tetel.getAfaalap();
            BigDecimal afaertek = tetel.getAfaertek();
            BigDecimal brutto = tetel.getBrutto();
            vatSummary.addToAfaAlap(afaalap.setScale(0, BigDecimal.ROUND_HALF_UP));
            vatSummary.addToAfaErtek(afaertek);
            vatSummary.addToBrutto(brutto);
            vatSummeries.putIfAbsent(tetel.getAfaSzazalek(), vatSummary);

            //Overall
            overallSummary.addToAfaAlap(afaalap.setScale(0, BigDecimal.ROUND_HALF_UP));
            overallSummary.addToAfaErtek(afaertek);
            overallSummary.addToBrutto(brutto);
        }
    }

    public boolean isStorno() {
        return stEredeti != null && !stEredeti.isEmpty();
    }

}
