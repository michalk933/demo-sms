package com.example.demosms.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmsRequest {

    private final String message;

    @JsonCreator
    public SmsRequest(@JsonProperty("message") String message) {
        this.message = message;
    }

}
