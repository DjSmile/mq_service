package ru.common.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 
 */
@Slf4j
public class JsonUtil {

    public static String toJSON(Object obj) {
        // convert to json
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        	log.error("Erro create json  ",e);
        }
        
        return jsonString;
    }

    public static Object fromJSON(String jsonString, Class<?> c) {
        ObjectMapper mapper = new ObjectMapper();
        Object o = null;
        try {
            o = mapper.readValue(jsonString, c);
        } catch (IOException e) {
        	log.error("Erro create obj from json ",e);
        }
        return o;
    }

}
