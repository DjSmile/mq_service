
package ru.pgu.mq_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RequestResponse {

    @JsonProperty("type")
    public String type;
    @JsonProperty("contentType")
    public String contentType;
    @JsonProperty("uid")
    public String uid;
    @JsonProperty("originalUid")
    public String originalUid;
    @JsonProperty("primaryContent")
    public PrimaryContent primaryContent;
    @JsonProperty("metadata")
    public Metadata metadata;
    @JsonIgnore
    public Long orderId;
    @JsonIgnore
    public Date completeOn;

}
