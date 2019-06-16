package com.example.demosms.handler;


import com.example.demosms.domain.Customer;
import com.example.demosms.service.CustomerService;
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
 * Handler to customer
 * </p>
 * <p>
 * Create by Michał Kuchciak
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerHandler {

    private final CustomerService customerService;

    public Mono<ServerResponse> addCustomer(final ServerRequest request) {
        Mono<Customer> customer = request.body(toMono(Customer.class));

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(customerService.addCustomer(customer)
                        , Customer.class);
    }

    public Mono<ServerResponse> getCustomerById(final ServerRequest request) {
        String customerId = request.pathVariable("customerId");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(customerService.getCustomerById(Mono.fromCallable(() -> customerId)), Customer.class);
    }

    public Mono<ServerResponse> deleteCustomerById(ServerRequest request) {
        String customerId = request.pathVariable("customerId");

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(customerService.deleteCustomer(Mono.fromCallable(() -> customerId)), Void.class);
    }

}
