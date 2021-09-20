package com.dreamworker.textanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDTO {

    @JsonProperty("message")
    private String message;

    @JsonProperty("httpStatusCode")
    private int status;

    public ErrorDTO(int status, String message){
        this.status = status;
        this.message = message;
    }
}
