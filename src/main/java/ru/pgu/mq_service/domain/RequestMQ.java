package ru.pgu.mq_service.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMQ implements Serializable {

	public static final String DATE_FMT = "yyyy-MM-dd'T'hh:mm:ss";

	@JsonIgnore
    private Long id;
    private String uid;
    private Long order_id;
    private Boolean cancel_allowed;
    private Boolean send_message_allowed;
    private String author;
    private String comment;
    private String org_code;
    private Long tech_code;
    private String requestType;
    
    @DateTimeFormat(pattern = DATE_FMT)
    private Date  registered_on;

}
