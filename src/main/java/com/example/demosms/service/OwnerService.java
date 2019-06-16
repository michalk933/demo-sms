package com.example.demosms.service;

import com.example.demosms.domain.Customer;
import com.example.demosms.domain.Owner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * owner service
 *
 * Create by Michał Kuchciak
 */
public interface OwnerService {

    /**
     * Add new owner
     *
     * @param newOwner owner
     * @return new owner or empty object
     */
    Mono<Owner> addOwner(final Mono<Owner> newOwner);

    /**
     * Get owner by id
     *
     * @param ownerId owner id
     * @return owner or empty object
     */
    Mono<Owner> getOwnerById(final Mono<String> ownerId);

    /**
     * Delete owner
     *
     * @param ownerId owner id to delete
     * @return
     */
    Mono<Void> deleteOwner(final Mono<String> ownerId);

    /**
     * Find customers for owner
     *
     * @param ownerId owner id
     * @return customers or empty object
     */
    Flux<Customer> getCustomerByOwnerId(final Mono<String> ownerId);

}
