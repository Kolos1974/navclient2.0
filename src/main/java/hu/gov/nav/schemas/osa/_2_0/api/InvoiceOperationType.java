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
 * Invoice operation of the request
 * 
 * <p>Java class for InvoiceOperationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceOperationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="index" type="{http://schemas.nav.gov.hu/OSA/2.0/data}IndexType"/>
 *         &lt;element name="invoiceOperation" type="{http://schemas.nav.gov.hu/OSA/2.0/api}ManageInvoiceOperationType"/>
 *         &lt;element name="invoiceData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceOperationType", propOrder = {
    "index",
    "invoiceOperation",
    "invoiceData"
})
public class InvoiceOperationType {

    protected int index;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ManageInvoiceOperationType invoiceOperation;
    @XmlElement(required = true)
    protected byte[] invoiceData;

    /**
     * Gets the value of the index property.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * Gets the value of the invoiceOperation property.
     * 
     * @return
     *     possible object is
     *     {@link ManageInvoiceOperationType }
     *     
     */
    public ManageInvoiceOperationType getInvoiceOperation() {
        return invoiceOperation;
    }

    /**
     * Sets the value of the invoiceOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManageInvoiceOperationType }
     *     
     */
    public void setInvoiceOperation(ManageInvoiceOperationType value) {
        this.invoiceOperation = value;
    }

    /**
     * Gets the value of the invoiceData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getInvoiceData() {
        return invoiceData;
    }

    /**
     * Sets the value of the invoiceData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setInvoiceData(byte[] value) {
        this.invoiceData = value;
    }

}
