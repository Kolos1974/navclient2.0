//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.13 at 05:51:16 PM UTC 
//


package hu.gov.nav.schemas.osa._2_0.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Data of a batch of modification documents
 * 
 * <p>Java class for BatchInvoiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchInvoiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchIndex" type="{http://schemas.nav.gov.hu/OSA/2.0/data}UnboundedIndexType"/>
 *         &lt;element name="invoice" type="{http://schemas.nav.gov.hu/OSA/2.0/data}InvoiceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchInvoiceType", propOrder = {
    "batchIndex",
    "invoice"
})
public class BatchInvoiceType {

    protected int batchIndex;
    @XmlElement(required = true)
    protected InvoiceType invoice;

    /**
     * Gets the value of the batchIndex property.
     * 
     */
    public int getBatchIndex() {
        return batchIndex;
    }

    /**
     * Sets the value of the batchIndex property.
     * 
     */
    public void setBatchIndex(int value) {
        this.batchIndex = value;
    }

    /**
     * Gets the value of the invoice property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceType }
     *     
     */
    public InvoiceType getInvoice() {
        return invoice;
    }

    /**
     * Sets the value of the invoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceType }
     *     
     */
    public void setInvoice(InvoiceType value) {
        this.invoice = value;
    }

}
