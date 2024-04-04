package com.example.backend.customer.model.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class FilterErrorResponse {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private boolean isSuccess;
    private int code;
    private String message;





    public String convertToJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
