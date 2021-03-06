//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.27 at 09:28:36 PM MSK 
//


package ru.pgu.mq_service.jaxbdomain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.pgu.mq_service.jaxbdomain package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EventServiceRequest_QNAME = new QName("http://epgu.gosuslugi.ru/lk/order/event/SCVDEV/3.1.0", "eventServiceRequest");
    private final static QName _EventServiceResponse_QNAME = new QName("http://epgu.gosuslugi.ru/lk/order/event/SCVDEV/3.1.0", "eventServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.pgu.mq_service.jaxbdomain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderStatusEvent }
     * 
     */
    public OrderStatusEvent createOrderStatusEvent() {
        return new OrderStatusEvent();
    }

    /**
     * Create an instance of {@link EventObject }
     * 
     */
    public EventObject createEventObject() {
        return new EventObject();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link PaymentStatusEvent }
     * 
     */
    public PaymentStatusEvent createPaymentStatusEvent() {
        return new PaymentStatusEvent();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link InfoEvent }
     * 
     */
    public InfoEvent createInfoEvent() {
        return new InfoEvent();
    }

    /**
     * Create an instance of {@link TextMessageEvent }
     * 
     */
    public TextMessageEvent createTextMessageEvent() {
        return new TextMessageEvent();
    }

    /**
     * Create an instance of {@link InvitationEvent }
     * 
     */
    public InvitationEvent createInvitationEvent() {
        return new InvitationEvent();
    }

    /**
     * Create an instance of {@link OrderStatusEvent.StatusCode }
     * 
     */
    public OrderStatusEvent.StatusCode createOrderStatusEventStatusCode() {
        return new OrderStatusEvent.StatusCode();
    }

    /**
     * Create an instance of {@link EventObject.Event }
     * 
     */
    public EventObject.Event createEventObjectEvent() {
        return new EventObject.Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://epgu.gosuslugi.ru/lk/order/event/SCVDEV/3.1.0", name = "eventServiceRequest")
    public JAXBElement<EventObject> createEventServiceRequest(EventObject value) {
        return new JAXBElement<EventObject>(_EventServiceRequest_QNAME, EventObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://epgu.gosuslugi.ru/lk/order/event/SCVDEV/3.1.0", name = "eventServiceResponse")
    public JAXBElement<Response> createEventServiceResponse(Response value) {
        return new JAXBElement<Response>(_EventServiceResponse_QNAME, Response.class, null, value);
    }

}
