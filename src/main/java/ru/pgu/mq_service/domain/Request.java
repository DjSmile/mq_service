
package ru.pgu.mq_service.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ru.common.amqp.message.request.meta.Metadata;
import ru.common.amqp.message.request.meta.PrimaryContent;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "contentType",
    "uid",
    "primaryContent",
    "metadata"
})
public class Request {

    @JsonProperty("type")
    private String type;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("primaryContent")
    private ru.common.amqp.message.request.meta.PrimaryContent primaryContent;
    @JsonProperty("metadata")
    private ru.common.amqp.message.request.meta.Metadata metadata;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("primaryContent")
    public ru.common.amqp.message.request.meta.PrimaryContent getPrimaryContent() {
        return primaryContent;
    }

    @JsonProperty("primaryContent")
    public void setPrimaryContent(PrimaryContent primaryContent) {
        this.primaryContent = primaryContent;
    }

    @JsonProperty("metadata")
    public ru.common.amqp.message.request.meta.Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
