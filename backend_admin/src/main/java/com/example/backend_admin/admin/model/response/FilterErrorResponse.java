package com.example.backend_admin.admin.model.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;

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
