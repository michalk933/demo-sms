package com.example.demosms.service.impl;

import com.example.demosms.service.TwilioService;
import com.google.common.cache.CacheLoader;
import com.twilio.rest.api.v2010.account.MessageCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Profile("!test")
@RequiredArgsConstructor
public class TwilioServiceImpl implements TwilioService {

    @Override
    public Mono<Void> sendSms(MessageCreator message) {
        Mono.fromCallable(message::create)
                .doOnSuccess(result -> log.info("Send sms: {}", result))
                .doOnError(throwable -> new CacheLoader.InvalidCacheLoadException("Phone number is not a valid number"));
        return Mono.empty();
    }
}
