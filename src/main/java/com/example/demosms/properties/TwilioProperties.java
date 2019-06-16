package com.example.demosms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;


@Data
@Configuration
@ConfigurationProperties("twilio")
public class TwilioProperties {

    @NotBlank
    private String accountSid;

    @NotBlank
    private String authToken;

    @NotBlank
    private String trialNumber;

}
