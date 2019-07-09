package ru.common.service;

import ru.pgu.mq_service.domain.RequestMQ;

public interface RequestMessageService {

	/**
	 * Build String request JSON from RequestMQ.  
	 * 
	 * @param requestMQ source for building JSON
	 * @return JSON result string or NULL
	 */
	String buildRequestJson(RequestMQ requestMQ);
}
