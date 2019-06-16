package com.example.demosms.service.impl;

import com.example.demosms.domain.Customer;
import com.example.demosms.domain.Owner;
import com.example.demosms.repository.OwnerRepository;
import com.example.demosms.service.CustomerService;
import com.example.demosms.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final CustomerService customerService;

    @Override
    public Mono<Owner> addOwner(final Mono<Owner> newOwner) {
        return newOwner
                .map(ownerRepository::insert)
                .flatMap(ownerMono -> newOwner)
                .doOnSuccess(owner -> log.info("Add owner: {}", owner))
                .doOnError(throwable -> new Owner());
    }

    @Override
    public Mono<Owner> getOwnerById(final Mono<String> ownerId) {
        return ownerId
                .map(this.ownerRepository::findById)
                .flatMap(ownerMono -> ownerMono)
                .doOnSuccess(owner -> log.info("Get owner: {}", owner))
                .doOnError(throwable -> new Owner());
    }

    @Override
    public Mono<Void> deleteOwner(final Mono<String> ownerId) {
        return this.getOwnerById(ownerId)
                .map(ownerRepository::delete)
                .flatMap(voidMono -> voidMono)
                .doOnSuccess(aVoid -> log.info("Delete owner"));
    }

    @Override
    public Flux<Customer> getCustomerByOwnerId(Mono<String> ownerId) {
        return customerService.getCustomerByOwnerId(ownerId);
    }
}
