package ru.pgu.mq_service.dao;

/**
 * Common type for every DB entity. 
 * 
 * @author Sergey Stotskiy
 * 
 */
public interface CommonDao<T> {

    T getById(Integer id);

	int insert(T data);

}
