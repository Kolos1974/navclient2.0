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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Response type of the POST /querySystemMetrics REST operation
 * 
 * <p>Java class for QueryServiceMetricsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryServiceMetricsResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.nav.gov.hu/OSA/2.0/api}BasicResponseType">
 *       &lt;sequence>
 *         &lt;element name="invoiceMetrics" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="metricsCreatedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryServiceMetricsResponseType", propOrder = {
    "invoiceMetrics",
    "metricsCreatedAt"
})
@XmlSeeAlso({
    QueryServiceMetricsResponse.class
})
public class QueryServiceMetricsResponseType
    extends BasicResponseType
{

    @XmlElement(required = true)
    protected byte[] invoiceMetrics;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar metricsCreatedAt;

    /**
     * Gets the value of the invoiceMetrics property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getInvoiceMetrics() {
        return invoiceMetrics;
    }

    /**
     * Sets the value of the invoiceMetrics property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setInvoiceMetrics(byte[] value) {
        this.invoiceMetrics = value;
    }

    /**
     * Gets the value of the metricsCreatedAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMetricsCreatedAt() {
        return metricsCreatedAt;
    }

    /**
     * Sets the value of the metricsCreatedAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMetricsCreatedAt(XMLGregorianCalendar value) {
        this.metricsCreatedAt = value;
    }

}
