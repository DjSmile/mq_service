package ru.pgu.mq_service.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.common.service.DbProcessingService;
import ru.common.service.RequestMessageService;
import ru.common.service.XmlService;
import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.ResponseMQ;

import javax.xml.bind.JAXBException;
import java.util.Date;


@Controller
@Slf4j
public class CRUDController {

	@Autowired
	XmlService xmlService;

	@Autowired
	DbProcessingService dbProcessingService;

	@Autowired
	RequestMessageService requestMessageService;

	/**
	 * Get responser by order id.
	 * 
	 * @param orderId id of request order.
	 * @return body 
	 */
	@RequestMapping(value = "/response/{orderId}/", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Object getResponse(@PathVariable Long orderId) {
		ResponseMQ response = dbProcessingService.getResponseByOrderId(orderId);
		if (response == null) {
			log.error("{\"message\":\"Response with this odredId is not exists\"}");
		}
		return response;
	}

	/**
	 * Publish response.
	 * 
	 * @deprecated  Have to debugging and implementing this method.
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/response/{orderId}/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Object saveResponse(@PathVariable Long orderId) {
		log.debug("Start parsing.");
		ResponseMQ response = null;
		try {
			//TODO  Have to debug and implemented.
			response = xmlService.parseResponseFile("src\\main\\resources\\xml\\samples\\Response0.xml", orderId,
					new Date());
		} catch (JAXBException e) {
			log.error("Error of reading  ", e);
		}
		if (response == null) {
			log.error("Response is null");
			return "Response is null";
		}

		Boolean existed = dbProcessingService.save(response);
		if (existed == false) {
			log.error("Duplicate key value odredId");
			return "Duplicate key value odredId";
		}
		log.debug("Parsed");
		return response;
	}

	/**
	 * Create and publish request.
	 * 
	 * @param requestMQ
	 * @return
	 */
	@RequestMapping(value = "request_test/", method = RequestMethod.POST)
	@ResponseBody
	public String request(@RequestBody RequestMQ requestMQ) {
		log.info("Start generating request.");
		return requestMessageService.buildRequestJson(requestMQ);
	}

}
