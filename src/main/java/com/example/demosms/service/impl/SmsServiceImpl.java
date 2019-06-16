package com.example.demosms.service.impl;

import com.example.demosms.domain.SmsRequest;
import com.example.demosms.properties.TwilioProperties;
import com.example.demosms.service.CustomerService;
import com.example.demosms.service.SmsService;
import com.example.demosms.service.TwilioService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final TwilioProperties twilioProperties;

    private final CustomerService customerService;

    private final TwilioService twilioService;

    @Override
    public Mono<Void> prepareSms(Mono<SmsRequest> smsRequest, Mono<String> ownerId) {
        Flux<String> phoneNumberByOwnerId = customerService.getPhoneNumberByOwnerId(ownerId);

        return Flux.zip(phoneNumberByOwnerId, smsRequest, (phoneNumber, request) -> Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioProperties.getTrialNumber()),
                request.getMessage()))
                .map(twilioService::sendSms)
                .then();
    }
}
