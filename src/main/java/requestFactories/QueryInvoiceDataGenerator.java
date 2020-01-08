package requestFactories;

import config.Config;
import exception.QueryInvoiceStatusGenException;
import exception.SHA512Exception;
import hu.gov.nav.schemas.osa._1_0.api.*;
import utils.Algos;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;

public class QueryInvoiceDataGenerator {

    private QueryInvoiceDataGenerator() { }

    public static final QueryInvoiceDataGenerator INSTANCE = new QueryInvoiceDataGenerator();

    public QueryInvoiceDataRequest generateObj(String invoiceNumber) throws QueryInvoiceStatusGenException {

        Instant now = Instant.now();
        String requestId = Common.getUid(now);
        try {
            XMLGregorianCalendar xmlGregorianCalendar = DateConverter.convertInstantToXmlGregorianCalendar(now);
            BasicHeaderType basicHeaderType = Common.getBasicHeaderType(requestId, xmlGregorianCalendar);
            UserHeaderType userHeaderType = Common.getUserHeaderTypeNormal(now, requestId);

            InvoiceQueryType invoiceQueryType = new InvoiceQueryType();
            invoiceQueryType.setInvoiceNumber(invoiceNumber);
//            //params...
//            InvoiceQueryParamsType invoiceQueryParamsType = new InvoiceQueryParamsType();
//            invoiceQueryParamsType.set
            QueryInvoiceDataRequest queryInvoiceDataRequest = new QueryInvoiceDataRequest();
            queryInvoiceDataRequest.setHeader(basicHeaderType);
            queryInvoiceDataRequest.setUser(userHeaderType);
            queryInvoiceDataRequest.setInvoiceQuery(invoiceQueryType);
            queryInvoiceDataRequest.setPage(1);
            return queryInvoiceDataRequest;
        } catch (SHA512Exception | DatatypeConfigurationException e) {
            throw new QueryInvoiceStatusGenException(e.getMessage());
        }

    }
}
