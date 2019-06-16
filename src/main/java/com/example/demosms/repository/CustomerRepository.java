package com.example.demosms.repository;

import com.example.demosms.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Flux<Customer> findByOwnerId(final String ownerId);

    Flux<String> findPhoneNumberByOwnerId(final String ownerId);

}
