//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.27 at 09:28:36 PM MSK 
//


package ru.pgu.mq_service.jaxbdomain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 Изменения статуса заявки в ЛК
 *             
 * 
 * <p>Java class for OrderStatusEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderStatusEvent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="statusCode"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="orgCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="techCode" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="cancelAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="sendMessageAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderStatusEvent", propOrder = {
    "statusCode",
    "cancelAllowed",
    "sendMessageAllowed"
})
public class OrderStatusEvent {

    @XmlElement(required = true)
    protected OrderStatusEvent.StatusCode statusCode;
    protected Boolean cancelAllowed;
    protected Boolean sendMessageAllowed;

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link OrderStatusEvent.StatusCode }
     *     
     */
    public OrderStatusEvent.StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderStatusEvent.StatusCode }
     *     
     */
    public void setStatusCode(OrderStatusEvent.StatusCode value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the cancelAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelAllowed() {
        return cancelAllowed;
    }

    /**
     * Sets the value of the cancelAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelAllowed(Boolean value) {
        this.cancelAllowed = value;
    }

    /**
     * Gets the value of the sendMessageAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSendMessageAllowed() {
        return sendMessageAllowed;
    }

    /**
     * Sets the value of the sendMessageAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSendMessageAllowed(Boolean value) {
        this.sendMessageAllowed = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;choice&gt;
     *         &lt;element name="orgCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="techCode" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orgCode",
        "techCode"
    })
    public static class StatusCode {

        protected String orgCode;
        protected Long techCode;

        /**
         * Gets the value of the orgCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrgCode() {
            return orgCode;
        }

        /**
         * Sets the value of the orgCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrgCode(String value) {
            this.orgCode = value;
        }

        /**
         * Gets the value of the techCode property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getTechCode() {
            return techCode;
        }

        /**
         * Sets the value of the techCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setTechCode(Long value) {
            this.techCode = value;
        }

    }

}
