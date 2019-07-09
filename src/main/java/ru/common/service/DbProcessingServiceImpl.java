package ru.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pgu.mq_service.dao.RequestDao;
import ru.pgu.mq_service.dao.ResponseDao;
import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.ResponseMQ;

@Service
@Slf4j
public class DbProcessingServiceImpl implements DbProcessingService {

	@Autowired
	RequestDao requestDao;

	@Autowired
	ResponseDao responseDao;

	@Transactional
	@Override
	public Boolean save(RequestMQ request) {
		RequestMQ duplicate = requestDao.getByUId(request.getUid());
		if (duplicate != null) {
			return false;
		}
		requestDao.insert(request);
		return true;
	}

	public RequestMQ getRequestByOrderId(Long orderId) {
		if (orderId == null) {
			log.error("orderId is null");
			return null;
		}
		return requestDao.getByOrderId(orderId);
	}

	@Transactional
	@Override
	public Boolean save(ResponseMQ response) {
		ResponseMQ duplicate = responseDao.getByOrderId(response.getOrder_id());
		if (duplicate != null) {
			return false;
		}
		responseDao.insertMQ(response);
		return true;
	}

	@Override
	public ResponseMQ getResponseByOrderId(Long orderId) {
		if (orderId == null) {
			log.error("orderId is null");
			return null;
		}
		return responseDao.getByOrderId(orderId);
	}
}
