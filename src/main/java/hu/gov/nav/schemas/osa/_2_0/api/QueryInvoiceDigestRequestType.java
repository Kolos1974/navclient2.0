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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Request type of the POST /queryInvoiceDigest REST operation
 * 
 * <p>Java class for QueryInvoiceDigestRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryInvoiceDigestRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.nav.gov.hu/OSA/2.0/api}BasicRequestType">
 *       &lt;sequence>
 *         &lt;element name="page" type="{http://schemas.nav.gov.hu/OSA/2.0/api}PageType"/>
 *         &lt;element name="invoiceDirection" type="{http://schemas.nav.gov.hu/OSA/2.0/api}InvoiceDirectionType"/>
 *         &lt;element name="invoiceQueryParams" type="{http://schemas.nav.gov.hu/OSA/2.0/api}InvoiceQueryParamsType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryInvoiceDigestRequestType", propOrder = {
    "page",
    "invoiceDirection",
    "invoiceQueryParams"
})
@XmlSeeAlso({
    QueryInvoiceDigestRequest.class
})
public class QueryInvoiceDigestRequestType
    extends BasicRequestType
{

    protected int page;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected InvoiceDirectionType invoiceDirection;
    @XmlElement(required = true)
    protected InvoiceQueryParamsType invoiceQueryParams;

    /**
     * Gets the value of the page property.
     * 
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     */
    public void setPage(int value) {
        this.page = value;
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
     * Gets the value of the invoiceQueryParams property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceQueryParamsType }
     *     
     */
    public InvoiceQueryParamsType getInvoiceQueryParams() {
        return invoiceQueryParams;
    }

    /**
     * Sets the value of the invoiceQueryParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceQueryParamsType }
     *     
     */
    public void setInvoiceQueryParams(InvoiceQueryParamsType value) {
        this.invoiceQueryParams = value;
    }

}
