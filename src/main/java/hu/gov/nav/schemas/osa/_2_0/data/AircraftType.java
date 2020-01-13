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
 * Aircraft
 * 
 * <p>Java class for AircraftType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AircraftType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="takeOffWeight" type="{http://schemas.nav.gov.hu/OSA/2.0/data}QuantityType"/>
 *         &lt;element name="airCargo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="operationHours" type="{http://schemas.nav.gov.hu/OSA/2.0/data}QuantityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AircraftType", propOrder = {
    "takeOffWeight",
    "airCargo",
    "operationHours"
})
public class AircraftType {

    @XmlElement(required = true)
    protected BigDecimal takeOffWeight;
    @XmlElement(defaultValue = "false")
    protected boolean airCargo;
    @XmlElement(required = true)
    protected BigDecimal operationHours;

    /**
     * Gets the value of the takeOffWeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTakeOffWeight() {
        return takeOffWeight;
    }

    /**
     * Sets the value of the takeOffWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTakeOffWeight(BigDecimal value) {
        this.takeOffWeight = value;
    }

    /**
     * Gets the value of the airCargo property.
     * 
     */
    public boolean isAirCargo() {
        return airCargo;
    }

    /**
     * Sets the value of the airCargo property.
     * 
     */
    public void setAirCargo(boolean value) {
        this.airCargo = value;
    }

    /**
     * Gets the value of the operationHours property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOperationHours() {
        return operationHours;
    }

    /**
     * Sets the value of the operationHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOperationHours(BigDecimal value) {
        this.operationHours = value;
    }

}
