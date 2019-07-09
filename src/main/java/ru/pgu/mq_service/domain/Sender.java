
package ru.pgu.mq_service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sender {

    @JsonProperty("mnemonic")
    public Object mnemonic;
    @JsonProperty("name")
    public String name;

}
