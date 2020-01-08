package utils;

//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import exception.XmlPrettifyException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class XmlFormatter {

    public static String prettify(String xml) throws XmlPrettifyException {
        return "";
//        try {
//            return "";
//            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(xml)));
//            OutputFormat format = new OutputFormat(doc);
//            format.setIndenting(true);
//            format.setIndent(2);
//            format.setLineWidth(Integer.MAX_VALUE);
//            Writer outxml = new StringWriter();
//            XMLSerializer serializer = new XMLSerializer(outxml, format);
//            serializer.serialize(doc);
//            return outxml.toString();
//        } catch (SAXException | IOException | ParserConfigurationException e) {
//            throw new XmlPrettifyException(e.getMessage());
//        }
    }
}
