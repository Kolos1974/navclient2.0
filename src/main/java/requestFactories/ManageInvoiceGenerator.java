package requestFactories;

import config.Config;
import data.entity.Szamla;
import exception.InvoiceRequestGenException;
import exception.SHA512Exception;
import hu.gov.nav.schemas.osa._2_0.api.*;
import hu.gov.nav.schemas.osa._2_0.data.InvoiceData;
import utils.Algos;
import utils.DateConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Instant;

public class ManageInvoiceGenerator {

    public static ManageInvoiceGenerator INSTANCE = new ManageInvoiceGenerator();

    private ManageInvoiceGenerator() {}

    private InvoiceDataGenerator invoiceDataGenerator = new InvoiceDataGenerator();

    public ManageInvoiceRequest generateObjFromEntity(Szamla szamla, String exchangeToken) throws InvoiceRequestGenException {
        InvoiceData invoice = invoiceDataGenerator.generateObject(szamla);
        if (szamla.isStorno())
            return createManageInvoiceRequest(invoice, exchangeToken, ManageInvoiceOperationType.STORNO);
        else return createManageInvoiceRequest(invoice, exchangeToken, null);
    }

    public ManageInvoiceRequest generateObjFromFile(Path path, String exchangeToken, ManageInvoiceOperationType type) throws InvoiceRequestGenException {
        File file = new File(path.toUri());
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InvoiceData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InvoiceData invoice = (InvoiceData) unmarshaller.unmarshal(file);
            return createManageInvoiceRequest(invoice, exchangeToken, type);
        } catch (JAXBException e) {
            throw new InvoiceRequestGenException("A file-t nem lehet beparszolni: " + path.toAbsolutePath() + " " + e.getMessage());
        }
    }

    private ManageInvoiceRequest createManageInvoiceRequest(InvoiceData invoice, String exchangeToken, ManageInvoiceOperationType type) throws InvoiceRequestGenException {
        if (type == null) type = ManageInvoiceOperationType.CREATE;
        InvoiceOperationListType invoiceOperationList = new InvoiceOperationListType();
//        invoiceOperationList.setTechnicalAnnulment(type == ManageInvoiceOperationType.ANNUL); // TODO
        invoiceOperationList.setCompressedContent(false);
        try {
            InvoiceOperationType invoiceOperation = new InvoiceOperationType();
            invoiceOperation.setIndex(1);
            String invoiceAsXmlString = invoiceToXmlString(invoice);
            String base64EncodedInvoice = Algos.encodeBase64(invoiceAsXmlString);
            String crs = Algos.getCRC3CheckSum(base64EncodedInvoice);
            invoiceOperation.setInvoiceData(invoiceAsXmlString.getBytes(StandardCharsets.UTF_8));
            invoiceOperation.setInvoiceOperation(type);
            invoiceOperationList.getInvoiceOperation().add(invoiceOperation);
            ManageInvoiceRequest manageInvoiceRequest = new ManageInvoiceRequest();
            manageInvoiceRequest.setInvoiceOperations(invoiceOperationList);
            manageInvoiceRequest.setExchangeToken(exchangeToken);

            Instant now = Instant.now();
            String requestId = Common.getUid(now);
            XMLGregorianCalendar xmlGregorianCalendar = DateConverter.convertInstantToXmlGregorianCalendar(now);

            BasicHeaderType basicHeaderType = Common.getBasicHeaderType(requestId, xmlGregorianCalendar);

            UserHeaderType userHeaderType = new UserHeaderType();
            userHeaderType.setLogin(Config.userName);
            userHeaderType.setPasswordHash(Config.getPasswordHash());
            userHeaderType.setTaxNumber(Config.taxNumber);
            userHeaderType.setRequestSignature(Algos.generateSha512From(
                    requestId + Common.getFormattedDate(now) + Config.signKey + crs));

            manageInvoiceRequest.setHeader(basicHeaderType);
            manageInvoiceRequest.setUser(userHeaderType);
            return manageInvoiceRequest;

        } catch ( JAXBException | SHA512Exception | DatatypeConfigurationException e) {
            throw new InvoiceRequestGenException("Hiba az InvoiceData generalas soran: " + e.getMessage());
        }
    }

    private String invoiceToXmlString(InvoiceData invoice) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(InvoiceData.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(invoice, stringWriter);
        return stringWriter.toString();
    }

}
