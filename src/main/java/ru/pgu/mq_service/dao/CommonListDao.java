package ru.pgu.mq_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Common type for entity contains list of objects.
 * 
 * @author Sergey Stotskiy
 */
public interface CommonListDao<T> extends CommonDao<T>{
    
    List<T> getListById(@Param("id") Integer id);

    int insertList(@Param("id") Integer id, @Param("items") List<T> data);

}
