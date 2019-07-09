package ru.common.amqp.message.request.meta;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pgu.mq_service.domain.Attach;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "primary",
    "attach"
})
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PrimaryContent {

    @JsonProperty("primary")
    private String primary;
    @JsonProperty("attach")
    private List<Attach> attach = null;

}
