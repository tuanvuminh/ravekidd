package com.ravekidd.v1.model.error;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private String status;

    private int statusCode;

    private List<String> errorMessages;

    public ErrorResponse(String status, int statusCode, List<String> errorMessages) {
        this.status = status;
        this.statusCode = statusCode;
        this.errorMessages = errorMessages;
    }

    public ErrorResponse(String status, int statusCode, String errorMessage) {
        this.status = status;
        this.statusCode = statusCode;
        this.errorMessages = List.of(errorMessage);
    }
}

