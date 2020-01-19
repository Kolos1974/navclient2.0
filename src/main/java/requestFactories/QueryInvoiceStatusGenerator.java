package requestFactories;

import exception.QueryInvoiceStatusGenException;
import exception.SHA512Exception;
import hu.gov.nav.schemas.osa._2_0.api.BasicHeaderType;
import hu.gov.nav.schemas.osa._2_0.api.QueryTransactionStatusRequest;
import hu.gov.nav.schemas.osa._2_0.api.UserHeaderType;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;

public class QueryInvoiceStatusGenerator {

    private QueryInvoiceStatusGenerator() { }

    public static final QueryInvoiceStatusGenerator INSTANCE = new QueryInvoiceStatusGenerator();

    public QueryTransactionStatusRequest generateObj(String transactionId) throws QueryInvoiceStatusGenException {
        Instant now = Instant.now();
        String requestId = Common.getUid(now);
        try {
            XMLGregorianCalendar xmlGregorianCalendar = DateConverter.convertInstantToXmlGregorianCalendar(now);
            BasicHeaderType basicHeaderType = Common.getBasicHeaderType(requestId, xmlGregorianCalendar);
            UserHeaderType userHeaderType = Common.getUserHeaderTypeNormal(now ,requestId);
            QueryTransactionStatusRequest QueryTransactionStatusRequest = new QueryTransactionStatusRequest();
            QueryTransactionStatusRequest.setReturnOriginalRequest(true);
            QueryTransactionStatusRequest.setTransactionId(transactionId);
            QueryTransactionStatusRequest.setHeader(basicHeaderType);
            QueryTransactionStatusRequest.setUser(userHeaderType);
            return QueryTransactionStatusRequest;
        } catch (SHA512Exception e) {
            throw new QueryInvoiceStatusGenException("SHA couldnt be generated!");
        } catch (DatatypeConfigurationException e) {
            throw new QueryInvoiceStatusGenException(e.getMessage());
        }
    }

}
