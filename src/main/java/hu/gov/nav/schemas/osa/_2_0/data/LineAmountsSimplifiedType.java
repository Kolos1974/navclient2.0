//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.13 at 05:51:16 PM UTC 
//


package hu.gov.nav.schemas.osa._2_0.data;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Item value data to be completed in case of simplified invoice
 * 
 * <p>Java class for LineAmountsSimplifiedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LineAmountsSimplifiedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineVatContent" type="{http://schemas.nav.gov.hu/OSA/2.0/data}RateType" minOccurs="0"/>
 *         &lt;element name="lineGrossAmountSimplified" type="{http://schemas.nav.gov.hu/OSA/2.0/data}MonetaryType"/>
 *         &lt;element name="lineGrossAmountSimplifiedHUF" type="{http://schemas.nav.gov.hu/OSA/2.0/data}MonetaryType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineAmountsSimplifiedType", propOrder = {
    "lineVatContent",
    "lineGrossAmountSimplified",
    "lineGrossAmountSimplifiedHUF"
})
public class LineAmountsSimplifiedType {

    protected BigDecimal lineVatContent;
    @XmlElement(required = true)
    protected BigDecimal lineGrossAmountSimplified;
    @XmlElement(required = true)
    protected BigDecimal lineGrossAmountSimplifiedHUF;

    /**
     * Gets the value of the lineVatContent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLineVatContent() {
        return lineVatContent;
    }

    /**
     * Sets the value of the lineVatContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLineVatContent(BigDecimal value) {
        this.lineVatContent = value;
    }

    /**
     * Gets the value of the lineGrossAmountSimplified property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLineGrossAmountSimplified() {
        return lineGrossAmountSimplified;
    }

    /**
     * Sets the value of the lineGrossAmountSimplified property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLineGrossAmountSimplified(BigDecimal value) {
        this.lineGrossAmountSimplified = value;
    }

    /**
     * Gets the value of the lineGrossAmountSimplifiedHUF property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLineGrossAmountSimplifiedHUF() {
        return lineGrossAmountSimplifiedHUF;
    }

    /**
     * Sets the value of the lineGrossAmountSimplifiedHUF property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLineGrossAmountSimplifiedHUF(BigDecimal value) {
        this.lineGrossAmountSimplifiedHUF = value;
    }

}
