package requestFactories;

import config.Config;
import exception.SHA512Exception;
import hu.gov.nav.schemas.osa._1_0.api.BasicHeaderType;
import hu.gov.nav.schemas.osa._1_0.api.UserHeaderType;
import utils.Algos;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Common {

    public static String getFormattedDate(Instant now) {
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.ofInstant(now, ZoneOffset.UTC));
    }

    public static String getUid(Instant now) {
        return "RID" +  getFormattedDate(now) + UUID.randomUUID().toString().replace("-", "").substring(0,10);
    }

    public static BasicHeaderType getBasicHeaderType(String requestId, XMLGregorianCalendar xmlGregorianCalendar) {
        BasicHeaderType basicHeaderType = new BasicHeaderType();
        basicHeaderType.setRequestId(requestId);
        basicHeaderType.setTimestamp(xmlGregorianCalendar.normalize());
        basicHeaderType.setRequestVersion(Config.requestVersion);
        basicHeaderType.setHeaderVersion(Config.headerVersion);
        return basicHeaderType;
    }

    public static UserHeaderType getUserHeaderTypeNormal(Instant now, String requestId) throws SHA512Exception {
        UserHeaderType userHeaderType = new UserHeaderType();
        userHeaderType.setLogin(Config.userName);
        userHeaderType.setPasswordHash(Config.getPasswordHash());
        userHeaderType.setTaxNumber(Config.taxNumber);
        userHeaderType.setRequestSignature(Algos.generateSha512From(requestId + Common.getFormattedDate(now) + Config.signKey));
        return userHeaderType;
    }

}
