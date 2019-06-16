package com.example.demosms.repository;


import com.example.demosms.domain.Owner;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OwnerRepository extends ReactiveMongoRepository<Owner, String> {
}
