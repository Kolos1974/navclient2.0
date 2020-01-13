//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.13 at 05:51:16 PM UTC 
//


package hu.gov.nav.schemas.osa._2_0.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SoftwareOperationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SoftwareOperationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LOCAL_SOFTWARE"/>
 *     &lt;enumeration value="ONLINE_SERVICE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SoftwareOperationType")
@XmlEnum
public enum SoftwareOperationType {


    /**
     * Local program
     * 
     */
    LOCAL_SOFTWARE,

    /**
     * Online billing service
     * 
     */
    ONLINE_SERVICE;

    public String value() {
        return name();
    }

    public static SoftwareOperationType fromValue(String v) {
        return valueOf(v);
    }

}
