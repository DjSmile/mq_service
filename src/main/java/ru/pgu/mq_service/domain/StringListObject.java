package ru.pgu.mq_service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringListObject {

    private Integer id;
    private String value;
    private Integer list_id;
    
    public StringListObject(String value) {
     this.value = value;        
    }
}
