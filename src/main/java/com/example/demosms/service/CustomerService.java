package com.example.demosms.service;

import com.example.demosms.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Customer service
 *
 * Create by Michał Kuchciak
 */
public interface CustomerService {

    /**
     * Add customer
     *
     * @param newCustomer new customer
     * @return new customer or empty object
     */
    Mono<Customer> addCustomer(final Mono<Customer> newCustomer);

    /**
     * Get customer by id
     *
     * @param id customer id
     * @return customer or empty object
     */
    Mono<Customer> getCustomerById(final Mono<String> id);

    /**
     * delete customer
     *
     * @param id customer id
     * @return
     */
    Mono<Void> deleteCustomer(final Mono<String> id);

    /**
     * Get customers by owner id
     *
     * @param ownerId owner id
     * @return Customers or empty object
     */
    Flux<Customer> getCustomerByOwnerId(final Mono<String> ownerId);


    /**
     * Get phone number by owner id
     *
     * @param ownerId owner id
     * @return Phone numbers
     */
    Flux<String> getPhoneNumberByOwnerId(final Mono<String> ownerId);
}
