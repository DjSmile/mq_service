
package ru.pgu.mq_service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Metadata {

    @JsonProperty("sender")
    public Sender sender;

}
