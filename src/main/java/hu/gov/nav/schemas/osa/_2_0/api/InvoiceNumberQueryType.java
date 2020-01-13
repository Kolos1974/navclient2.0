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
 * Invoice number param of the Invoice query
 * 
 * <p>Java class for InvoiceNumberQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceNumberQueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceNumber" type="{http://schemas.nav.gov.hu/OSA/2.0/data}SimpleText50NotBlankType"/>
 *         &lt;element name="invoiceDirection" type="{http://schemas.nav.gov.hu/OSA/2.0/api}InvoiceDirectionType"/>
 *         &lt;element name="batchIndex" type="{http://schemas.nav.gov.hu/OSA/2.0/data}UnboundedIndexType" minOccurs="0"/>
 *         &lt;element name="supplierTaxNumber" type="{http://schemas.nav.gov.hu/OSA/2.0/data}TaxpayerIdType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceNumberQueryType", propOrder = {
    "invoiceNumber",
    "invoiceDirection",
    "batchIndex",
    "supplierTaxNumber"
})
public class InvoiceNumberQueryType {

    @XmlElement(required = true)
    protected String invoiceNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected InvoiceDirectionType invoiceDirection;
    protected Integer batchIndex;
    protected String supplierTaxNumber;

    /**
     * Gets the value of the invoiceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the invoiceDirection property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceDirectionType }
     *     
     */
    public InvoiceDirectionType getInvoiceDirection() {
        return invoiceDirection;
    }

    /**
     * Sets the value of the invoiceDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceDirectionType }
     *     
     */
    public void setInvoiceDirection(InvoiceDirectionType value) {
        this.invoiceDirection = value;
    }

    /**
     * Gets the value of the batchIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBatchIndex() {
        return batchIndex;
    }

    /**
     * Sets the value of the batchIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBatchIndex(Integer value) {
        this.batchIndex = value;
    }

    /**
     * Gets the value of the supplierTaxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierTaxNumber() {
        return supplierTaxNumber;
    }

    /**
     * Sets the value of the supplierTaxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierTaxNumber(String value) {
        this.supplierTaxNumber = value;
    }

}
