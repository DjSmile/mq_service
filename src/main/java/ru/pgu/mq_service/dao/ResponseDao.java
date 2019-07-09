package ru.pgu.mq_service.dao;

import ru.pgu.mq_service.domain.ResponseMQ;

/**
 * DAO for Response entity.
 * 
 * @author Sergey Stotskiy
 *
 */
public interface ResponseDao extends CommonDao<ResponseMQ> {

	ResponseMQ getByOrderId(Long orderId);

	int insertMQ(ResponseMQ data);

}
