package com.example.demosms.handler;

import com.example.demosms.domain.SmsRequest;
import com.example.demosms.service.CustomerService;
import com.example.demosms.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;


/**
 * <p>
 * Handler to service sms
 * </p>
 * <p>
 * Create by Michał Kuchciak
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SmsHandler {

    private final SmsService smsService;

    private final CustomerService customerService;

    /**
     * Handler into send sms all clients for user
     *
     * @param request
     * @return Status ok
     */
    public Mono<ServerResponse> sendSms(ServerRequest request) {
        Mono<SmsRequest> smsRequest = request.body(toMono(SmsRequest.class));
        String ownerId = request.pathVariable("ownerId");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(smsService.prepareSms(smsRequest, Mono.fromCallable(() -> ownerId)), Void.class);
    }

    /**
     * list of number phone by owner id
     *
     * @param request
     * @return list of number phone
     */
    public Mono<ServerResponse> getNumberPhoneForOwner(ServerRequest request) {
        String ownerId = request.pathVariable("ownerId");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(customerService.getPhoneNumberByOwnerId(Mono.fromCallable(() -> ownerId)), String.class);

    }

}
