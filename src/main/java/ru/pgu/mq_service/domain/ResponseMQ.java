package ru.pgu.mq_service.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ResponseMQ {

	public static final String DATE_FMT = "yyyy-MM-dd'T'hh:mm:ss";
	
	@JsonIgnore
    private Long id;
    
    private Long order_id;

    private String message;

    private Long code;
    
    @DateTimeFormat(pattern = DATE_FMT)
    @JsonFormat(pattern="dd-MM-yyyy hh:mm")
    private Date  complete_on;

    @JsonIgnore
    private String uid;

}
