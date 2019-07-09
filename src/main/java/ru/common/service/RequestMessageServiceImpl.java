package ru.common.service;

import java.util.Date;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import ru.common.amqp.message.request.meta.*;
import ru.common.utils.json.JsonUtil;
import ru.pgu.mq_service.domain.Recipient;
import ru.pgu.mq_service.domain.Request;
import ru.pgu.mq_service.domain.RequestMQ;


@Slf4j
@Service
public class RequestMessageServiceImpl implements RequestMessageService {

	private static final String ORDER_ID_IS_NULL = "OrderId is null.";
	private static final String TECH_CODE_OR_ORG_CODE_IS_EMPTY = "TechCode or orgCode is empty";
	private static final String DUPLICATE_KEY_VALUE_UUID = "Duplicate key value uuid";
	
	@Autowired
	XmlService xmlService;
	TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

	@Autowired
	DbProcessingService dbProcessingService;
	
	public String buildRequestJson(RequestMQ requestMQ) {
		if (requestMQ.getOrder_id() == null) {
			log.error(ORDER_ID_IS_NULL);
			return ORDER_ID_IS_NULL;
		}

		if (requestMQ.getRegistered_on() == null) {
			requestMQ.setRegistered_on(new Date());
		}


		if (requestMQ.getTech_code() == null && StringUtils.isEmpty(requestMQ.getOrg_code())) {
			log.error(TECH_CODE_OR_ORG_CODE_IS_EMPTY);
			return TECH_CODE_OR_ORG_CODE_IS_EMPTY;
		}
		if(requestMQ.getUid() == null ||  requestMQ.getUid().isEmpty()) {
			String uid = gen.generate().toString();
			requestMQ.setUid(uid);
		}
		Boolean result = dbProcessingService.save(requestMQ);
		if (result == false) {
			log.error(DUPLICATE_KEY_VALUE_UUID + requestMQ.getOrder_id());
			return null;
		}

		String resultXml = xmlService.generateChangeStatusRequest(requestMQ);
		log.debug("Generating request finished:", resultXml);
		
		if(resultXml == null) {
			return null;
		}
		return prepareJson(resultXml, requestMQ.getUid());
	}

	private String prepareJson(String resultXml, String uid) {
		Request jsonRequestData = new Request();
		PrimaryContent primaryContent = new PrimaryContent();
		primaryContent.setPrimary(resultXml);
		jsonRequestData.setPrimaryContent(primaryContent);
		jsonRequestData.setUid(uid);
		jsonRequestData.setContentType("1");

		Metadata metadata = new Metadata();
		Sender sender = new Sender("MNSV00", "vcdec)");
		metadata.setSender(sender);
		metadata.setRecipient(new Recipient("MVDR01", "services"));
		jsonRequestData.setMetadata(metadata);
		jsonRequestData.setType("1");
		return JsonUtil.toJSON(jsonRequestData);
	}

	
}
