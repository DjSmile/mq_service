package ru.common.service;

import java.util.Date;

import javax.xml.bind.JAXBException;

import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.ResponseMQ;

/**
 * Service for work with xml.
 * 
 * @author Sergey Stotskiy
 *
 */
public interface XmlService {

	/**
	 * Parse response file.
	 * 
	 * @param xmlPath
	 * @param orderId
	 * @param complete_on
	 * @return
	 * @throws JAXBException
	 */
    ResponseMQ parseResponseFile(String xmlPath, Long orderId, Date complete_on) throws JAXBException;
    
    /**
     * 
     * @param xml
     * @param orderId
     * @param complete_on
     * @return
     * @throws JAXBException
     */
	ResponseMQ parseResponse(String xml, Long orderId, Date complete_on) throws JAXBException;

	/**
	 * Generate change status request.
	 * 
	 * @param requestMQ
	 * @return result string or NULL
	 */
	String generateChangeStatusRequest(RequestMQ requestMQ);
	
}
