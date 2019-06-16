package com.example.demosms.service.impl;

import com.example.demosms.domain.Customer;
import com.example.demosms.repository.CustomerRepository;
import com.example.demosms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> addCustomer(final Mono<Customer> newCustomer) {
        return newCustomer
                .map(customerRepository::insert)
                .flatMap(customerMono -> customerMono)
                .doOnSuccess(customer -> log.info("Add customer: {}", customer))
                .doOnError(throwable -> new Customer());
    }

    @Override
    public Mono<Customer> getCustomerById(final Mono<String> id) {
        return id
                .map(this.customerRepository::findById)
                .flatMap(customerMono -> customerMono)
                .doOnSuccess(customer -> log.info("Get customer: {}", customer))
                .doOnError(throwable -> new Customer());
    }

    @Override
    public Mono<Void> deleteCustomer(final Mono<String> id) {
        return this.getCustomerById(id)
                .map(customerRepository::delete)
                .flatMap(voidMono -> voidMono)
                .doOnSuccess(aVoid -> log.info("Delete customer"));
    }

    @Override
    public Flux<Customer> getCustomerByOwnerId(Mono<String> ownerId) {
        return ownerId
                .flatMapMany(customerRepository::findByOwnerId)
                .doOnError(throwable -> Flux.just(new Customer()));
    }

    @Override
    public Flux<String> getPhoneNumberByOwnerId(Mono<String> ownerId) {
        return ownerId
                .map(customerRepository::findPhoneNumberByOwnerId)
                .flatMapMany(stringFlux -> stringFlux)
                .doOnError(throwable -> Flux.just(""));
    }
}
