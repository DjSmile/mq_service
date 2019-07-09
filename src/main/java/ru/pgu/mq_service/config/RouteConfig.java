package ru.pgu.mq_service.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.common.service.processors.RequestResponseProc;
import ru.common.service.processors.RestRequestProc;
import ru.pgu.mq_service.domain.RequestMQ;
import ru.pgu.mq_service.domain.RequestResponse;


@Configuration
public class RouteConfig extends SingleRouteCamelConfiguration {

    @Autowired
    RestRequestProc requestProc;
    @Autowired
    RequestResponseProc reqRespProc;

    @Override
    public RouteBuilder route() {
        return new RouteBuilder() {


            @Override
            public void configure() throws Exception {
                PropertiesComponent pc = new PropertiesComponent();
                pc.setLocation("classpath:rabbitmq.properties");
                camelContext().addComponent("properties", pc);

                //request from REST to RabbitMQ
                from("servlet://request?")
                        .routeId("requestREST_AMQP")
                        .unmarshal().json(JsonLibrary.Jackson, RequestMQ.class)
                        .process(requestProc)
                        .choice().when(exchangeProperty("send").isEqualTo(true))
                        .to("direct:sendRequestAMQP")
                        .end()
                        .setBody(exchangeProperty("success"));

                //request from RabbitMQ to RabbitMQ
                from("rabbitmq://{{in.request.zor.amqp.host}}:{{in.request.zor.amqp.port}}/{{in.request.zor.amqp.exchangeName}}?username={{in.request.zor.amqp.username}}&password={{in.request.zor.amqp.password}}&queue={{in.request.zor.amqp.queueName}}&prefetchEnabled=true&automaticRecoveryEnabled=true&declare=false")
                        .routeId("requestAMQP_AMQP")
                        .unmarshal().json(JsonLibrary.Jackson, RequestMQ.class)
                        .choice().when(exchangeProperty("send").isEqualTo(true))
                        .to("direct:sendRequestAMQP")
                        .end()
                        .log(exchangeProperty("success").toString());


                from("rabbitmq://{{in.response.zor.amqp.host}}:{{in.response.zor.amqp.port}}/{{in.response.zor.amqp.exchangeName}}?username={{in.response.zor.amqp.username}}&password={{in.response.zor.amqp.password}}&queue={{in.response.zor.amqp.queueName}}&prefetchEnabled=true&automaticRecoveryEnabled=true&declare=false")
                        .routeId("responseAMQP_DB")
                        .unmarshal().json(JsonLibrary.Jackson, RequestResponse.class)
                        .process(reqRespProc);

                from("direct:sendRequestAMQP")
                        .to("rabbitmq://{{out.request.zor.amqp.host}}:{{out.request.zor.amqp.port}}/{{out.request.zor.amqp.exchangeName}}?routingKey={{out.request.zor.amqp.routingKey}}&declare=false&username={{out.request.zor.amqp.username}}&password={{out.request.zor.amqp.password}}&automaticRecoveryEnabled=true");


            }
        };
    }
}
