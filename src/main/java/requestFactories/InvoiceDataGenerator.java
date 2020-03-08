package requestFactories;

import config.Config;
import data.entity.*;
import exception.InvoiceRequestGenException;
import hu.gov.nav.schemas.osa._2_0.data.*;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

// Example: https://github.com/nav-gov-hu/Online-Invoice/blob/master/sample/Data%20sample/01_Belf%C3%B6ldi%20term%C3%A9k%C3%A9rt%C3%A9kes%C3%ADt%C3%A9s.xml

public class InvoiceDataGenerator {

    public InvoiceData generateObject(Szamla szamla) throws InvoiceRequestGenException {
        InvoiceData invoice = new InvoiceData();
        //<supplierInfo>
        SupplierInfoType supplierInfo = new SupplierInfoType();
        //<supplierTaxNumber>
        TaxNumberType supplierTaxNumber = new TaxNumberType();
        supplierTaxNumber.setTaxpayerId(Config.sajatCegAdoszam.substring(0, 8));
        supplierTaxNumber.setVatCode(Config.sajatCegAdoszam.substring(9, 10));
        supplierTaxNumber.setCountyCode(Config.sajatCegAdoszam.substring(11, 13));
        supplierInfo.setSupplierTaxNumber(supplierTaxNumber);
        //</supplierTaxNumber>

        //<supplierAddress>
        supplierInfo.setSupplierName(Config.sajatCegNeve);
        AddressType supplierAddress = new AddressType();
        DetailedAddressType supplierDetailedAddress = new DetailedAddressType();
        supplierDetailedAddress.setCountryCode(Config.sajatCountryCode);
        supplierDetailedAddress.setPostalCode(Config.sajatCegIrsz);
        supplierDetailedAddress.setCity(Config.sajatCegVaros);
        supplierDetailedAddress.setStreetName(Config.sajatCegCim);
        supplierDetailedAddress.setPublicPlaceCategory(Config.sajatCegKozterulet);
        supplierDetailedAddress.setNumber(Config.sajatCegHazszam);
        supplierAddress.setDetailedAddress(supplierDetailedAddress);
        supplierInfo.setSupplierAddress(supplierAddress);
        //</supplierAddress>
        //</supplierInfo>

        //<customerInfo>
        VeSza vesza = szamla.getVeSza();
        supplierInfo.setSupplierBankAccountNumber(szamla.getSzamlaSzam());
        //<customerTaxNumber>
        TaxNumberType customerTaxNumber = new TaxNumberType();
        customerTaxNumber.setTaxpayerId(vesza.getAdoszam().substring(0, 8));
        customerTaxNumber.setVatCode(vesza.getAdoszam().substring(9, 10));
        customerTaxNumber.setCountyCode(vesza.getAdoszam().substring(11, 13));
        CustomerInfoType customerInfo = new CustomerInfoType();
        customerInfo.setCustomerTaxNumber(customerTaxNumber);
        //</customerTaxNumber>
        customerInfo.setCustomerName(vesza.getMegnev());
        DetailedAddressType customerDetailedAddress = new DetailedAddressType();
        customerDetailedAddress.setCountryCode("HU");
        customerDetailedAddress.setPostalCode(vesza.getIrsz());
        customerDetailedAddress.setCity(vesza.getHelyseg());
        customerDetailedAddress.setStreetName(vesza.getUtca());
        customerDetailedAddress.setPublicPlaceCategory(vesza.getKozterulet());
        customerDetailedAddress.setNumber(vesza.getHazszam());
        AddressType customerAddress = new AddressType();
        customerAddress.setDetailedAddress(customerDetailedAddress);
        customerInfo.setCustomerAddress(customerAddress);
        //</customerInfo>
        InvoiceDetailType invoiceDetail = new InvoiceDetailType();
        invoiceDetail.setInvoiceCategory(InvoiceCategoryType.NORMAL);
        boolean isSzamlaV = szamla.getType() == Szamla.SzamlaType.V;
        boolean isSzamlaDV = szamla.isDeviza();
        try {
            XMLGregorianCalendar invoiceIssueDate = DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzdat());
            invoiceIssueDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            invoice.setInvoiceIssueDate(invoiceIssueDate);
            XMLGregorianCalendar paymentDate = DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getEsdat());
            paymentDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            invoiceDetail.setPaymentDate(paymentDate);
            XMLGregorianCalendar invoiceDeliveryDate = DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getTeljdat());
            invoiceDeliveryDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            invoiceDetail.setInvoiceDeliveryDate(invoiceDeliveryDate);
            if (!isSzamlaV && !isSzamlaDV) {
                invoiceDetail.setInvoiceDeliveryPeriodStart(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzidoszTol()));
                invoiceDetail.setInvoiceDeliveryPeriodEnd(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzidoszIg()));
                invoiceDetail.setInvoiceAccountingDeliveryDate(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzidoszIg()));
            }
        } catch (DatatypeConfigurationException e) {
            throw new InvoiceRequestGenException(e.getMessage());
        }
        invoiceDetail.setCurrencyCode(szamla.getDevizaNem());
        invoiceDetail.setExchangeRate(BigDecimal.valueOf(szamla.getExchangeRate()));
        invoiceDetail.setPaymentMethod(szamla.getFizmodkod().equals("2") ?
                PaymentMethodType.TRANSFER : PaymentMethodType.CASH);
        invoiceDetail.setInvoiceAppearance(InvoiceAppearanceType.PAPER);

        InvoiceHeadType invoiceHead = new InvoiceHeadType();
        invoiceHead.setSupplierInfo(supplierInfo);
        invoiceHead.setCustomerInfo(customerInfo);
        invoiceHead.setInvoiceDetail(invoiceDetail);
        //</invoiceHead>

        BigDecimal bigDecimal100 = new BigDecimal(100);

        //<invoiceLines>
        LinesType lines = new LinesType();
        List<SzamlaTetel> tetels = szamla.getTetels();
        for (SzamlaTetel tetel : tetels) {
            LineType line = new LineType();
            if (tetel.getKozvSzolg().equals("Igen")) line.setIntermediatedService(true);
            line.setLineNumber(BigInteger.valueOf(tetel.getTetelsorsz()));
            line.setLineExpressionIndicator(tetel.getTetelKitoltve());
            line.setLineDescription(tetel.getMegnev());
            line.setQuantity(tetel.getMennyiseg());
            /*
            if (tetel.getMe() == null || tetel.getMe().equals("")) line.setUnitOfMeasure("db");
            else line.setUnitOfMeasure(tetel.getMe());
            */
            if (isNAVUnitOfMeasure(tetel.getMe().toUpperCase().trim())) {
                line.setUnitOfMeasure(unitOfMeasure(tetel.getMe().toUpperCase().trim()));
            } else {
                line.setUnitOfMeasure(UnitOfMeasureType.OWN);
                if (tetel.getMe() == null || tetel.getMe().equals("")) line.setUnitOfMeasureOwn("PIECE");
                else line.setUnitOfMeasureOwn(tetel.getMe().toUpperCase().trim());
            }

            line.setUnitPrice(tetel.getEgysegAr());
            LineAmountsNormalType lineAmountsNormal = new LineAmountsNormalType();
            LineNetAmountDataType lineNetAmountData = new LineNetAmountDataType();
            lineNetAmountData.setLineNetAmount(isSzamlaDV ? tetel.getDevizaAfaalap() : tetel.getAfaalap());
            lineNetAmountData.setLineNetAmountHUF(tetel.getAfaalap());
            lineAmountsNormal.setLineNetAmountData(lineNetAmountData);
            VatRateType vatRate = new VatRateType();
            vatRate.setVatPercentage(tetel.getAfaSzazalek().divide(bigDecimal100).stripTrailingZeros());
            lineAmountsNormal.setLineVatRate(vatRate);
            LineVatDataType lineVatData = new LineVatDataType();
            lineVatData.setLineVatAmount(isSzamlaDV ? tetel.getDevizaAfaertek() : tetel.getAfaertek());
            lineVatData.setLineVatAmountHUF(tetel.getAfaertek());
            lineAmountsNormal.setLineVatData(lineVatData);
            LineGrossAmountDataType lineGrossAmountData = new LineGrossAmountDataType();
            lineGrossAmountData.setLineGrossAmountNormal(isSzamlaDV ? tetel.getDevizaBrutto() : tetel.getBrutto());
            lineGrossAmountData.setLineGrossAmountNormalHUF(tetel.getBrutto());
            lineAmountsNormal.setLineGrossAmountData(lineGrossAmountData);
            line.setLineAmountsNormal(lineAmountsNormal);
            if (szamla.isStorno() || szamla.isModified()) {
                LineModificationReferenceType lineModificationReferenceType = new LineModificationReferenceType();
                if (szamla.isStorno()) {
                    int numberOfTetels = szamla.getTetels().size();
                    lineModificationReferenceType.setLineNumberReference(BigInteger.valueOf(tetel.getTetelsorsz() + numberOfTetels));
                } else {
                    lineModificationReferenceType.setLineNumberReference(BigInteger.valueOf(tetel.getTetelsorsz() + szamla.getEredetiTetelCount()));
                }
                lineModificationReferenceType.setLineOperation(LineOperationType.CREATE);
                line.setLineModificationReference(lineModificationReferenceType);
            }
            lines.getLine().add(line);
        }
        //</invoiceLines>

        //<invoiceSummary>
        Map<BigDecimal, VatSummary> vatSummeries = szamla.getVatSummeries();
        SummaryType invoiceSummary = new SummaryType();
        SummaryNormalType summaryNormal = new SummaryNormalType();
        for (Map.Entry<BigDecimal, VatSummary> vatSummaryEntry : vatSummeries.entrySet()) {
            VatSummary value = vatSummaryEntry.getValue();
            SummaryByVatRateType summaryByVatRate = new SummaryByVatRateType();
            VatRateType vatRateSum = new VatRateType();
            vatRateSum.setVatPercentage(value.getAfaSzazalek().divide(bigDecimal100).stripTrailingZeros());
            summaryByVatRate.setVatRate(vatRateSum);
            VatRateNetDataType vatRateNetData = new VatRateNetDataType();
            vatRateNetData.setVatRateNetAmount(isSzamlaDV ? value.getDevizaAfaalapSum() : value.getAfaalapSum());
            vatRateNetData.setVatRateNetAmountHUF(value.getAfaalapSum());
            summaryByVatRate.setVatRateNetData(vatRateNetData);
            VatRateVatDataType vatRateVatData = new VatRateVatDataType();
            vatRateVatData.setVatRateVatAmount(isSzamlaDV ? value.getDevizaAfaertekSum() : value.getAfaertekSum());
            vatRateVatData.setVatRateVatAmountHUF(value.getAfaertekSum());
            summaryByVatRate.setVatRateVatData(vatRateVatData);
            VatRateGrossDataType vatRateGrossData = new VatRateGrossDataType();
            vatRateGrossData.setVatRateGrossAmount(isSzamlaDV ? value.getDevizaBruttoSum() : value.getBruttoSum());
            vatRateGrossData.setVatRateGrossAmountHUF(value.getBruttoSum());
            summaryByVatRate.setVatRateGrossData(vatRateGrossData);
            summaryNormal.getSummaryByVatRate().add(summaryByVatRate);
        }
        //</summaryByVatRate>
        OverallSummary overallSummary = szamla.getOverallSummary();
        summaryNormal.setInvoiceNetAmount(isSzamlaDV ?
                overallSummary.getDevizaAfaalapOverallSum() : overallSummary.getAfaalapOverallSum());
        summaryNormal.setInvoiceNetAmountHUF(overallSummary.getAfaalapOverallSum());
        summaryNormal.setInvoiceVatAmount(isSzamlaDV ?
                overallSummary.getDevizaAfaertekOverallSum() : overallSummary.getAfaertekOverallSum());
        summaryNormal.setInvoiceVatAmountHUF(overallSummary.getAfaertekOverallSum());
        //</summaryNormal>
        invoiceSummary.setSummaryNormal(summaryNormal);
        // <summaryGrossData>
        SummaryGrossDataType summaryGrossData = new SummaryGrossDataType();
        summaryGrossData.setInvoiceGrossAmount(isSzamlaDV ?
                overallSummary.getDevizaBruttoOverallSum() : overallSummary.getBruttoOverallSum());
        summaryGrossData.setInvoiceGrossAmountHUF(overallSummary.getBruttoOverallSum());
        invoiceSummary.setSummaryGrossData(summaryGrossData);
        //</invoiceSummary>

        // <invoice>
        InvoiceType invoiceType = new InvoiceType();
        invoiceType.setInvoiceHead(invoiceHead);
        invoiceType.setInvoiceLines(lines);
        invoiceType.setInvoiceSummary(invoiceSummary);

        // invoiceMain
        InvoiceMainType invoiceMain = new InvoiceMainType();
        invoiceMain.setInvoice(invoiceType);

        //ST_EREDETI VAGY MOD_EREDETI SZEREPEL
        if (szamla.isStorno() || szamla.isModified()) {
            InvoiceReferenceType invoiceReferenceType = new InvoiceReferenceType();
            if (szamla.isStorno()) invoiceReferenceType.setOriginalInvoiceNumber(szamla.getStEredeti());
            else invoiceReferenceType.setOriginalInvoiceNumber(szamla.getModEredeti());
            invoiceReferenceType.setModifyWithoutMaster(false);
            invoiceReferenceType.setModificationIndex(1);
            invoiceType.setInvoiceReference(invoiceReferenceType);
        }
        invoice.setInvoiceNumber(szamla.getIktSzam());
        invoice.setInvoiceMain(invoiceMain);
        return invoice;
    }


    public static UnitOfMeasureType unitOfMeasure(String measure) {

        UnitOfMeasureType NAVUnitOfMeasure = null;

        switch (measure.toUpperCase().trim()) {
            case "DB":
                NAVUnitOfMeasure = UnitOfMeasureType.PIECE;
                break;
            case "KG":
                NAVUnitOfMeasure = UnitOfMeasureType.KILOGRAM;
                break;
            case "T":
                NAVUnitOfMeasure = UnitOfMeasureType.TON;
                break;
            case "KWH":
                NAVUnitOfMeasure = UnitOfMeasureType.KWH;
                break;
            case "NAP":
                NAVUnitOfMeasure = UnitOfMeasureType.DAY;
                break;
            case "�RA":
                NAVUnitOfMeasure = UnitOfMeasureType.HOUR;
                break;
            case "PERC":
                NAVUnitOfMeasure = UnitOfMeasureType.MINUTE;
                break;
            case "H�":
                NAVUnitOfMeasure = UnitOfMeasureType.MONTH;
                break;
            case "LITER":
                NAVUnitOfMeasure = UnitOfMeasureType.LITER;
                break;
            case "KILOMETER":
                NAVUnitOfMeasure = UnitOfMeasureType.KILOMETER;
                break;
            case "M3":
                NAVUnitOfMeasure = UnitOfMeasureType.CUBIC_METER;
                break;
            case "M":
                NAVUnitOfMeasure = UnitOfMeasureType.METER;
                break;
            case "LM":
                NAVUnitOfMeasure = UnitOfMeasureType.LINEAR_METER;
                break;
            case "KARTON":
                NAVUnitOfMeasure = UnitOfMeasureType.CARTON;
                break;
            case "CSOMAG":
                NAVUnitOfMeasure = UnitOfMeasureType.PACK;
                break;

            // Missing:
            // A	 - AMPER
            // ALKAL - OPPORTUNITY
            // �V	 - YEAR
            // M2    - SQUARE_METER
            // Q 	 - QUINTAL

            default:
                NAVUnitOfMeasure = UnitOfMeasureType.OWN;
                break;
        }
        return NAVUnitOfMeasure;
    }

    public static boolean isNAVUnitOfMeasure(String unitOfLine) {
        return !(unitOfMeasure(unitOfLine) == UnitOfMeasureType.OWN);
    }


}
