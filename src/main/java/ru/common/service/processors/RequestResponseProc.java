package ru.common.service.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.common.service.DbProcessingService;
import ru.common.service.XmlService;
import ru.pgu.mq_service.dao.RequestDao;
import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.RequestResponse;
import ru.pgu.mq_service.domain.ResponseMQ;

import javax.xml.bind.JAXBException;
import java.util.Date;

/**
 * Service for receiving messages over Camel.
 * 
 * @author Sergey Stotskiy
 *
 */

@Slf4j
@Service
public class RequestResponseProc implements Processor {

    @Autowired
    DbProcessingService dbService;
    @Autowired
    XmlService xmlService;
    @Autowired
    RequestDao requestDao;

    @Override
    public void process(Exchange exchange) throws Exception {
        RequestResponse response = exchange.getIn().getBody(RequestResponse.class);

        RequestMQ requestMQ = requestDao.getByUId(response.getOriginalUid());
        if(requestMQ == null) {
            log.info("request is null for uuid: ", response.getUid());
        } else {
            try {
                ResponseMQ responseMQ = xmlService.parseResponse(response.getPrimaryContent().getPrimary(), requestMQ.getOrder_id(),
                        new Date());
                responseMQ.setUid(response.getOriginalUid());
                dbService.save(responseMQ);
            } catch (JAXBException e) {
                log.error("Error of reading  ", e);
            }
        }
    }
}
