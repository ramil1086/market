//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.08.24 at 10:57:25 PM EET 
//


package ru.gb.market.soap.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="categorysoap" type="{http://www.marketapp.ru/spring/ws/categories}categorysoap"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "categorysoap"
})
@XmlRootElement(name = "getCategoryByIdResponse")
public class GetCategoryByIdResponse {

    @XmlElement(required = true)
    protected Categorysoap categorysoap;

    /**
     * Gets the value of the categorysoap property.
     * 
     * @return
     *     possible object is
     *     {@link Categorysoap }
     *     
     */
    public Categorysoap getCategorysoap() {
        return categorysoap;
    }

    /**
     * Sets the value of the categorysoap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Categorysoap }
     *     
     */
    public void setCategorysoap(Categorysoap value) {
        this.categorysoap = value;
    }

}
