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
import javax.xml.bind.annotation.XmlType;


/**
 * Data in header of invoice
 * 
 * <p>Java class for InvoiceHeadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceHeadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplierInfo" type="{http://schemas.nav.gov.hu/OSA/1.0/data}SupplierInfoType"/>
 *         &lt;element name="customerInfo" type="{http://schemas.nav.gov.hu/OSA/1.0/data}CustomerInfoType" minOccurs="0"/>
 *         &lt;element name="fiscalRepresentativeInfo" type="{http://schemas.nav.gov.hu/OSA/1.0/data}FiscalRepresentativeType" minOccurs="0"/>
 *         &lt;element name="invoiceData" type="{http://schemas.nav.gov.hu/OSA/1.0/data}InvoiceDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceHeadType", propOrder = {
    "supplierInfo",
    "customerInfo",
    "fiscalRepresentativeInfo",
    "invoiceData"
})
public class InvoiceHeadType {

    @XmlElement(required = true)
    protected SupplierInfoType supplierInfo;
    protected CustomerInfoType customerInfo;
    protected FiscalRepresentativeType fiscalRepresentativeInfo;
    @XmlElement(required = true)
    protected InvoiceDataType invoiceData;

    /**
     * Gets the value of the supplierInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SupplierInfoType }
     *     
     */
    public SupplierInfoType getSupplierInfo() {
        return supplierInfo;
    }

    /**
     * Sets the value of the supplierInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierInfoType }
     *     
     */
    public void setSupplierInfo(SupplierInfoType value) {
        this.supplierInfo = value;
    }

    /**
     * Gets the value of the customerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInfoType }
     *     
     */
    public CustomerInfoType getCustomerInfo() {
        return customerInfo;
    }

    /**
     * Sets the value of the customerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInfoType }
     *     
     */
    public void setCustomerInfo(CustomerInfoType value) {
        this.customerInfo = value;
    }

    /**
     * Gets the value of the fiscalRepresentativeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FiscalRepresentativeType }
     *     
     */
    public FiscalRepresentativeType getFiscalRepresentativeInfo() {
        return fiscalRepresentativeInfo;
    }

    /**
     * Sets the value of the fiscalRepresentativeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiscalRepresentativeType }
     *     
     */
    public void setFiscalRepresentativeInfo(FiscalRepresentativeType value) {
        this.fiscalRepresentativeInfo = value;
    }

    /**
     * Gets the value of the invoiceData property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceDataType }
     *     
     */
    public InvoiceDataType getInvoiceData() {
        return invoiceData;
    }

    /**
     * Sets the value of the invoiceData property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceDataType }
     *     
     */
    public void setInvoiceData(InvoiceDataType value) {
        this.invoiceData = value;
    }

}
