
package ru.pgu.mq_service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PrimaryContent {

    @JsonProperty("primary")
    public String primary;
    @JsonProperty("attach")
    public List<Object> attach = null;

}
