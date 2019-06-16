package com.example.demosms.configuration;

import com.example.demosms.properties.TwilioProperties;
import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration twilio
 *
 * Create by Michał Kuchciak
 */
@Configuration
@Slf4j
public class TwilioInitialazer {

    private final TwilioProperties twilioConfiguration;

    public TwilioInitialazer(TwilioProperties twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );

        log.info("Twilio initialized ...with account sid {}", twilioConfiguration.getAccountSid());
    }
}
