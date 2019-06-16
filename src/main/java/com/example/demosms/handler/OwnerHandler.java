package com.example.demosms.handler;


import com.example.demosms.domain.Customer;
import com.example.demosms.domain.Owner;
import com.example.demosms.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;


/**
 * <p>
 * Handler to service owner
 * </p>
 *
 * Create by Michał Kuchciak
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerHandler {

    private final OwnerService ownerService;

    public Mono<ServerResponse> addOwner(ServerRequest request) {
        Mono<Owner> owner = request.body(toMono(Owner.class));

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(ownerService.addOwner(owner), Owner.class);
    }

    public Mono<ServerResponse> deleteOwner(ServerRequest request) {
        String ownerId = request.pathVariable("ownerId");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(ownerService.deleteOwner(Mono.fromCallable(() -> ownerId)), Void.class);

    }

    public Mono<ServerResponse> getOwnerById(ServerRequest request) {
        String ownerId = request.pathVariable("ownerId");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(ownerService.getOwnerById(Mono.fromCallable(() -> ownerId)), Owner.class);

    }

    public Mono<ServerResponse> getCustomerByOwnerId(ServerRequest request) {
        String ownerId = request.pathVariable("ownerId");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(ownerService.getCustomerByOwnerId(Mono.fromCallable(() -> ownerId)), Customer.class);

    }


}
