//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.13 at 05:51:16 PM UTC 
//


package hu.gov.nav.schemas.osa._2_0.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Billing software data
 * 
 * <p>Java class for SoftwareType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SoftwareType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="softwareId" type="{http://schemas.nav.gov.hu/OSA/2.0/api}SoftwareIdType"/>
 *         &lt;element name="softwareName" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText50NotBlankType"/>
 *         &lt;element name="softwareOperation" type="{http://schemas.nav.gov.hu/OSA/2.0/api}SoftwareOperationType"/>
 *         &lt;element name="softwareMainVersion" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText15NotBlankType"/>
 *         &lt;element name="softwareDevName" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText512NotBlankType"/>
 *         &lt;element name="softwareDevContact" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText200NotBlankType"/>
 *         &lt;element name="softwareDevCountryCode" type="{http://schemas.nav.gov.hu/OSA/2.0/data}CountryCodeType" minOccurs="0"/>
 *         &lt;element name="softwareDevTaxNumber" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText50NotBlankType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoftwareType", propOrder = {
    "softwareId",
    "softwareName",
    "softwareOperation",
    "softwareMainVersion",
    "softwareDevName",
    "softwareDevContact",
    "softwareDevCountryCode",
    "softwareDevTaxNumber"
})
public class SoftwareType {

    @XmlElement(required = true)
    protected String softwareId;
    @XmlElement(required = true)
    protected String softwareName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected SoftwareOperationType softwareOperation;
    @XmlElement(required = true)
    protected String softwareMainVersion;
    @XmlElement(required = true)
    protected String softwareDevName;
    @XmlElement(required = true)
    protected String softwareDevContact;
    protected String softwareDevCountryCode;
    protected String softwareDevTaxNumber;

    /**
     * Gets the value of the softwareId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareId() {
        return softwareId;
    }

    /**
     * Sets the value of the softwareId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareId(String value) {
        this.softwareId = value;
    }

    /**
     * Gets the value of the softwareName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareName() {
        return softwareName;
    }

    /**
     * Sets the value of the softwareName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareName(String value) {
        this.softwareName = value;
    }

    /**
     * Gets the value of the softwareOperation property.
     * 
     * @return
     *     possible object is
     *     {@link SoftwareOperationType }
     *     
     */
    public SoftwareOperationType getSoftwareOperation() {
        return softwareOperation;
    }

    /**
     * Sets the value of the softwareOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareOperationType }
     *     
     */
    public void setSoftwareOperation(SoftwareOperationType value) {
        this.softwareOperation = value;
    }

    /**
     * Gets the value of the softwareMainVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareMainVersion() {
        return softwareMainVersion;
    }

    /**
     * Sets the value of the softwareMainVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareMainVersion(String value) {
        this.softwareMainVersion = value;
    }

    /**
     * Gets the value of the softwareDevName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareDevName() {
        return softwareDevName;
    }

    /**
     * Sets the value of the softwareDevName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareDevName(String value) {
        this.softwareDevName = value;
    }

    /**
     * Gets the value of the softwareDevContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareDevContact() {
        return softwareDevContact;
    }

    /**
     * Sets the value of the softwareDevContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareDevContact(String value) {
        this.softwareDevContact = value;
    }

    /**
     * Gets the value of the softwareDevCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareDevCountryCode() {
        return softwareDevCountryCode;
    }

    /**
     * Sets the value of the softwareDevCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareDevCountryCode(String value) {
        this.softwareDevCountryCode = value;
    }

    /**
     * Gets the value of the softwareDevTaxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareDevTaxNumber() {
        return softwareDevTaxNumber;
    }

    /**
     * Sets the value of the softwareDevTaxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareDevTaxNumber(String value) {
        this.softwareDevTaxNumber = value;
    }

}
