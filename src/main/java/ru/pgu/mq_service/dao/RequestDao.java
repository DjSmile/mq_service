package ru.pgu.mq_service.dao;

import org.apache.ibatis.annotations.Param;

import ru.pgu.mq_service.domain.RequestMQ;

/**
 * DAO for Request entity
 * 
 * @author Sergey Stotskiy
 *
 */
public interface RequestDao extends CommonDao<RequestMQ> {

	RequestMQ getByOrderId(@Param("order_id") Long order_id);

	RequestMQ getByUId(String uuid);
	
}
