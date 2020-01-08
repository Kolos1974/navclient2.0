package requestFactories;

import config.Config;
import data.entity.*;
import exception.InvoiceRequestGenException;
import hu.gov.nav.schemas.osa._1_0.data.*;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class InvoiceGenerator {

    public Invoice generateObject(Szamla szamla) throws InvoiceRequestGenException {
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

        InvoiceDataType invoiceData = new InvoiceDataType();
        invoiceData.setInvoiceNumber(szamla.getIktSzam());
        invoiceData.setInvoiceCategory(InvoiceCategoryType.NORMAL);
        try {
            XMLGregorianCalendar invoiceIssueDate = DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzdat());
            invoiceIssueDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            invoiceData.setInvoiceIssueDate(invoiceIssueDate);
            XMLGregorianCalendar paymentDate = DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getEsdat());
            paymentDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            invoiceData.setPaymentDate(paymentDate);
            XMLGregorianCalendar invoiceDeliveryDate = DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getTeljdat());
            invoiceDeliveryDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            invoiceData.setInvoiceDeliveryDate(invoiceDeliveryDate);
            if (szamla.getType() != Szamla.SzamlaType.V) {
                invoiceData.setInvoiceDeliveryPeriodStart(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzidoszTol()));
                invoiceData.setInvoiceDeliveryPeriodEnd(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzidoszIg()));
                invoiceData.setInvoiceAccountingDeliveryDate(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzidoszIg()));
            }
        } catch (DatatypeConfigurationException e) {
            throw new InvoiceRequestGenException(e.getMessage());
        }
        invoiceData.setCurrencyCode(szamla.getCurrencyCode());
        invoiceData.setExchangeRate(szamla.getExchRate());
        invoiceData.setPaymentMethod(szamla.getFizmodkod().equals("2") ?
                PaymentMethodType.TRANSFER : PaymentMethodType.CASH);
        invoiceData.setInvoiceAppearance(InvoiceAppearanceType.PAPER);

        InvoiceHeadType invoiceHead = new InvoiceHeadType();
        invoiceHead.setSupplierInfo(supplierInfo);
        invoiceHead.setCustomerInfo(customerInfo);
        invoiceHead.setInvoiceData(invoiceData);
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
            }
            else
            {
            	line.setUnitOfMeasure(UnitOfMeasureType.OWN);
                if (tetel.getMe() == null || tetel.getMe().equals("")) line.setUnitOfMeasureOwn("PIECE");
                else line.setUnitOfMeasureOwn(tetel.getMe().toUpperCase().trim());
            }
            
            line.setUnitPrice(tetel.getEgysegAr());
            LineAmountsNormalType lineAmountsNormal = new LineAmountsNormalType();
            lineAmountsNormal.setLineNetAmount(tetel.getAfaalap());
            VatRateType vatRate = new VatRateType();
            vatRate.setVatPercentage(tetel.getAfaSzazalek().divide(bigDecimal100).stripTrailingZeros());
            lineAmountsNormal.setLineVatRate(vatRate);
            lineAmountsNormal.setLineVatAmount(tetel.getAfaertek());
            lineAmountsNormal.setLineGrossAmountNormal(tetel.getBrutto());
            line.setLineAmountsNormal(lineAmountsNormal);
            if (szamla.isStorno()) {
                LineModificationReferenceType lineModificationReferenceType = new LineModificationReferenceType();
                int numberOfTetels = szamla.getTetels().size();
                lineModificationReferenceType.setLineNumberReference(BigInteger.valueOf(tetel.getTetelsorsz() + numberOfTetels));
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
            summaryByVatRate.setVatRateNetAmount(value.getAfaalapSum());
            summaryByVatRate.setVatRateVatAmount(value.getAfaertekSum());
            summaryByVatRate.setVatRateVatAmountHUF(value.getAfaertekSum());
            summaryByVatRate.setVatRateGrossAmount(value.getBruttoSum());
            summaryNormal.getSummaryByVatRate().add(summaryByVatRate);
        }
        //</summaryByVatRate>
        OverallSummary overallSummary = szamla.getOverallSummary();
        summaryNormal.setInvoiceNetAmount(overallSummary.getAfaalapOverallSum());
        summaryNormal.setInvoiceVatAmount(overallSummary.getAfaertekOverallSum());
        summaryNormal.setInvoiceVatAmountHUF(overallSummary.getAfaertekOverallSum());
        //</summaryNormal>
        invoiceSummary.setSummaryNormal(summaryNormal);
        invoiceSummary.setInvoiceGrossAmount(overallSummary.getBruttoOverallSum());
        //</invoiceSummary>

        InvoiceExchangeType invoiceExchange = new InvoiceExchangeType();
        invoiceExchange.setInvoiceHead(invoiceHead);
        invoiceExchange.setInvoiceLines(lines);
        invoiceExchange.setInvoiceSummary(invoiceSummary);

        //ST_EREDETI SZEREPEL
        if (szamla.isStorno()) {
            InvoiceReferenceType invoiceReferenceType = new InvoiceReferenceType();
            invoiceReferenceType.setOriginalInvoiceNumber(szamla.getStEredeti());
            invoiceReferenceType.setModifyWithoutMaster(false);
            try {
                invoiceReferenceType.setModificationTimestamp(DateConverter.convertTimestampToXmlGregorianCalendarWithUTC(szamla.getBekdat()));
                invoiceReferenceType.setModificationIssueDate(DateConverter.convertTimestampToXmlGregorianCalendarNoUTC(szamla.getSzdat()));
            } catch (DatatypeConfigurationException e) {
                throw new InvoiceRequestGenException(e.getMessage());
            }
            invoiceExchange.setInvoiceReference(invoiceReferenceType);
        }

        Invoice invoice = new Invoice();
        invoice.setInvoiceExchange(invoiceExchange);
        return invoice;
    }

    
	public static UnitOfMeasureType unitOfMeasure(String measure) {
		
		UnitOfMeasureType NAVUnitOfMeasure=null; 		
		
		switch (measure.toUpperCase().trim()){
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
		case "ÓRA":
			NAVUnitOfMeasure = UnitOfMeasureType.HOUR;
			break;
		case "PERC":
			NAVUnitOfMeasure = UnitOfMeasureType.MINUTE;
			break;
		case "HÓ":
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
		// ÉV	 - YEAR
		// M2    - SQUARE_METER
		// Q 	 - QUINTAL
		
		default:
			NAVUnitOfMeasure = UnitOfMeasureType.OWN;
			break;
		}
		return NAVUnitOfMeasure;
	}
    
	public static boolean isNAVUnitOfMeasure(String unitOfLine){
		if (!(unitOfMeasure(unitOfLine)==UnitOfMeasureType.OWN)) {
			return true;
		}
		else
		{
			return false;
		}
	}
    

}
