package com.example.demosms.service;

import com.example.demosms.domain.SmsRequest;
import reactor.core.publisher.Mono;

/**
 * Sms service
 *
 * Create by Michał Kuchciak
 */
public interface SmsService {

    /**
     * Send sms to customers
     *
     * @param smsRequest data about sms
     * @param ownerId owner id
     */
    Mono<Void> prepareSms(final Mono<SmsRequest> smsRequest, Mono<String> ownerId);

}
