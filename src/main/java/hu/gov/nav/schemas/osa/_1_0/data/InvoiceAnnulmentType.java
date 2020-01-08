//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.05 at 09:47:06 AM CEST 
//


package hu.gov.nav.schemas.osa._1_0.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Data of technical annulment concerning previous data exchange
 * 
 * <p>Java class for InvoiceAnnulmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceAnnulmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annulmentReference" type="{http://schemas.nav.gov.hu/OSA/1.0/data}SimpleText50NotBlankType"/>
 *         &lt;element name="annulmentTimestamp" type="{http://schemas.nav.gov.hu/OSA/1.0/data}TimestampType"/>
 *         &lt;element name="annulmentCode" type="{http://schemas.nav.gov.hu/OSA/1.0/data}AnnulmentCodeType"/>
 *         &lt;element name="annulmentReason" type="{http://schemas.nav.gov.hu/OSA/1.0/data}SimpleText1024NotBlankType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceAnnulmentType", propOrder = {
    "annulmentReference",
    "annulmentTimestamp",
    "annulmentCode",
    "annulmentReason"
})
public class InvoiceAnnulmentType {

    @XmlElement(required = true)
    protected String annulmentReference;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar annulmentTimestamp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AnnulmentCodeType annulmentCode;
    @XmlElement(required = true)
    protected String annulmentReason;

    /**
     * Gets the value of the annulmentReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnulmentReference() {
        return annulmentReference;
    }

    /**
     * Sets the value of the annulmentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnulmentReference(String value) {
        this.annulmentReference = value;
    }

    /**
     * Gets the value of the annulmentTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnnulmentTimestamp() {
        return annulmentTimestamp;
    }

    /**
     * Sets the value of the annulmentTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnnulmentTimestamp(XMLGregorianCalendar value) {
        this.annulmentTimestamp = value;
    }

    /**
     * Gets the value of the annulmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link AnnulmentCodeType }
     *     
     */
    public AnnulmentCodeType getAnnulmentCode() {
        return annulmentCode;
    }

    /**
     * Sets the value of the annulmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnulmentCodeType }
     *     
     */
    public void setAnnulmentCode(AnnulmentCodeType value) {
        this.annulmentCode = value;
    }

    /**
     * Gets the value of the annulmentReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnulmentReason() {
        return annulmentReason;
    }

    /**
     * Sets the value of the annulmentReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnulmentReason(String value) {
        this.annulmentReason = value;
    }

}
