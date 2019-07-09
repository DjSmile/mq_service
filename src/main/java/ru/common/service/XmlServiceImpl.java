package ru.common.service;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.ResponseMQ;
import ru.pgu.mq_service.jaxbdomain.EventObject;
import ru.pgu.mq_service.jaxbdomain.EventObject.Event;
import ru.pgu.mq_service.jaxbdomain.ObjectFactory;
import ru.pgu.mq_service.jaxbdomain.OrderStatusEvent;
import ru.pgu.mq_service.jaxbdomain.OrderStatusEvent.StatusCode;
import ru.pgu.mq_service.jaxbdomain.Response;
import ru.pgu.mq_service.jaxbdomain.eventserviceresponse.EventServiceResponse;

/**
 * Service for working with XML formatted objects and files.
 * 
 * @author Sergey Stotskiy
 *
 */
@Service
@Slf4j
public class XmlServiceImpl implements XmlService {

	private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

	@Override
	public ResponseMQ parseResponseFile(String xmlPath, Long orderId, Date complete_on) throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller um = jc.createUnmarshaller();

		@SuppressWarnings("unchecked")
		JAXBElement<Response> jaxbElement = (JAXBElement<Response>) (um.unmarshal(new File(xmlPath)));

		Response xmlResponse = (Response) jaxbElement.getValue();

		xmlResponse.getCode();
		xmlResponse.getMessage();

		ResponseMQ response = new ResponseMQ(null, orderId, xmlResponse.getMessage(), xmlResponse.getCode(),
				complete_on, null);
		return response;
	}

	@Override
	public ResponseMQ parseResponse(String xml, Long orderId, Date complete_on) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EventServiceResponse.class);
		Unmarshaller um = context.createUnmarshaller();
		EventServiceResponse xmlResponse = (EventServiceResponse) um.unmarshal(new StringReader(xml));
		ResponseMQ response = new ResponseMQ(null, orderId, xmlResponse.getMessage(), xmlResponse.getCode(),
				complete_on, null);
		return response;
	}

	@Override
	public String generateChangeStatusRequest(RequestMQ requestMQ) {

		XMLGregorianCalendar xmlGregorianCalendar = createXmlGregorianCalendar(requestMQ);
		if (xmlGregorianCalendar == null) {
			log.error("Couldn't create a calendar.");
			return null;
		}

		try {
			ObjectFactory factory = new ObjectFactory();
			EventObject eventObject = factory.createEventObject();

			eventObject.setOrderId(requestMQ.getOrder_id());
			eventObject.setEventDate(xmlGregorianCalendar);
			eventObject.setEventComment(requestMQ.getComment());
			eventObject.setEventAuthor(requestMQ.getAuthor());

			Event event = factory.createEventObjectEvent();
			eventObject.setEvent(event);
			OrderStatusEvent orderStatusEvent = factory.createOrderStatusEvent();
			event.setOrderStatusEvent(orderStatusEvent);

			StatusCode statusCode = factory.createOrderStatusEventStatusCode();
			orderStatusEvent.setCancelAllowed(requestMQ.getCancel_allowed());
			orderStatusEvent.setSendMessageAllowed(requestMQ.getSend_message_allowed());
			orderStatusEvent.setStatusCode(statusCode);
			statusCode.setOrgCode(requestMQ.getOrg_code());
			statusCode.setTechCode(requestMQ.getTech_code());

			JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);

			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter stringWriter = new StringWriter();
			stringWriter.write(XML_HEADER);
			marshaller.marshal(factory.createEventServiceRequest(eventObject), stringWriter);

			log.info("Result xml" + stringWriter.toString());
			return stringWriter.toString();
		} catch (JAXBException e) {
			log.error("JAXB errors happened.", e);
			return null;
		}
	}

	/**
	 * Create XmlGregorianCalendar.
	 *  
	 * @param requestMQ parameter
	 * @return instance of calendar or NULL
	 */
	private XMLGregorianCalendar createXmlGregorianCalendar(RequestMQ requestMQ) {
		Date registered_on = requestMQ.getRegistered_on();
		XMLGregorianCalendar xmlGregorianCalendar = null;
		try {
			DatatypeFactory dataFactory = DatatypeFactory.newInstance();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(registered_on);
			xmlGregorianCalendar = dataFactory.newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			log.error(" Couldn't create a calendar", e);
		}
		return xmlGregorianCalendar;
	}

}
