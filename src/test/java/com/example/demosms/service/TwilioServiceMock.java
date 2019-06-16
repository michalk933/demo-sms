package com.example.demosms.service;

import com.twilio.rest.api.v2010.account.MessageCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Profile("test")
@Service
public class TwilioServiceMock implements TwilioService
{
    @Override
    public Mono<Void> sendSms(MessageCreator message) {
        log.info("Mock TwilioService: {}", message);
        return Mono.empty();
    }
}
