package ru.common.service;

import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.ResponseMQ;

/**
 * Service for working with DB.
 * 
 * @author Sergey Stotskiy
 *
 */
public interface DbProcessingService {
	

	/**
	 * Get Request by OrderId from DB.
	 * 
	 * @param orderId 
	 * @return requestMQ by orderId or NULL 
	 */
	RequestMQ getRequestByOrderId(Long orderId);

	/**
	 * Save request entity in DB.
	 * 
	 * @param request request
	 * @return status of operation: TRUE is OK else FALSE
	 */
	Boolean save(RequestMQ request);

	/**
	 * Get response entity by orderId.
	 * 
	 * @param orderId 
	 * @return responseMQ with orderId or NULL.
	 */
	ResponseMQ getResponseByOrderId(Long orderId);

	/**
	 * Save response entity in DB. 
	 * 
	 * @param response responseMQ for saving.
	 * @return status of operation: TRUE is OK else FALSE
	 */
	Boolean save(ResponseMQ response);

}
