//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.05 at 09:47:06 AM CEST 
//


package hu.gov.nav.schemas.osa._1_0.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarginSchemeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MarginSchemeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TRAVEL_AGENCY"/>
 *     &lt;enumeration value="SECOND_HAND"/>
 *     &lt;enumeration value="ARTWORK"/>
 *     &lt;enumeration value="ANTIQUES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MarginSchemeType")
@XmlEnum
public enum MarginSchemeType {


    /**
     * Travel agencies
     * 
     */
    TRAVEL_AGENCY,

    /**
     * Second-hand goods
     * 
     */
    SECOND_HAND,

    /**
     * Works of art
     * 
     */
    ARTWORK,

    /**
     * Collector�s items and antiques
     * 
     */
    ANTIQUES;

    public String value() {
        return name();
    }

    public static MarginSchemeType fromValue(String v) {
        return valueOf(v);
    }

}
