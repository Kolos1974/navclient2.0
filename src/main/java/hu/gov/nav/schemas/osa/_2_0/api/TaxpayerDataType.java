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
import javax.xml.bind.annotation.XmlType;
import hu.gov.nav.schemas.osa._2_0.data.DetailedAddressType;


/**
 * Response data of the taxpayer query
 * 
 * <p>Java class for TaxpayerDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxpayerDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taxpayerName" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText512NotBlankType"/>
 *         &lt;element name="taxpayerShortName" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText200NotBlankType" minOccurs="0"/>
 *         &lt;element name="vatGroupMembership" type="{http://schemas.nav.gov.hu/OSA/2.0/data}TaxpayerIdType" minOccurs="0"/>
 *         &lt;element name="taxpayerAddress" type="{http://schemas.nav.gov.hu/OSA/2.0/data}DetailedAddressType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxpayerDataType", propOrder = {
    "taxpayerName",
    "taxpayerShortName",
    "vatGroupMembership",
    "taxpayerAddress"
})
public class TaxpayerDataType {

    @XmlElement(required = true)
    protected String taxpayerName;
    protected String taxpayerShortName;
    protected String vatGroupMembership;
    protected DetailedAddressType taxpayerAddress;

    /**
     * Gets the value of the taxpayerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxpayerName() {
        return taxpayerName;
    }

    /**
     * Sets the value of the taxpayerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxpayerName(String value) {
        this.taxpayerName = value;
    }

    /**
     * Gets the value of the taxpayerShortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxpayerShortName() {
        return taxpayerShortName;
    }

    /**
     * Sets the value of the taxpayerShortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxpayerShortName(String value) {
        this.taxpayerShortName = value;
    }

    /**
     * Gets the value of the vatGroupMembership property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatGroupMembership() {
        return vatGroupMembership;
    }

    /**
     * Sets the value of the vatGroupMembership property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatGroupMembership(String value) {
        this.vatGroupMembership = value;
    }

    /**
     * Gets the value of the taxpayerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link DetailedAddressType }
     *     
     */
    public DetailedAddressType getTaxpayerAddress() {
        return taxpayerAddress;
    }

    /**
     * Sets the value of the taxpayerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailedAddressType }
     *     
     */
    public void setTaxpayerAddress(DetailedAddressType value) {
        this.taxpayerAddress = value;
    }

}
