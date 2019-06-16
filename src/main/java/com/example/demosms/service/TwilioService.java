package com.example.demosms.service;

import com.twilio.rest.api.v2010.account.MessageCreator;
import reactor.core.publisher.Mono;

public interface TwilioService {

    /**
     * Send sms
     *
     * @param message
     * @return
     */
    Mono<Void> sendSms(MessageCreator message);

}
