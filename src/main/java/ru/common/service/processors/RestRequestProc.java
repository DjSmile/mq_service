package ru.common.service.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.common.service.RequestMessageService;
import ru.pgu.mq_service.domain.RequestMQ;

/**
 * Service for receiving request type messages over Camel.
 * 
 * @author Sergey Stotskiy
 *
 */

@Slf4j
@Service
public class RestRequestProc implements Processor {

    @Autowired
    RequestMessageService requestMessageService;

    @Override
    public void process(Exchange exchange) throws Exception {
        RequestMQ restRequest = exchange.getIn().getBody(RequestMQ.class);
        String requestJson = requestMessageService.buildRequestJson(restRequest);

        exchange.getOut().setBody(requestJson);
        if(requestJson.contains("{")) {
            exchange.setProperty("success", "{ \"success\" : true}");
            exchange.setProperty("send", true);
        } else {
            exchange.setProperty("success", "{ \"orderId\": \"" + restRequest.getOrder_id() + "\" " + "\"success\" : false, \"error\": \"" + requestJson + "\" }");
            exchange.setProperty("send", false);
        }
    }
}
