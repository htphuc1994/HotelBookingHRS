package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.domain.entity.AppUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<AppUser, Long> {
    // Custom database queries can be defined here
    Mono<AppUser> findByUsername(String username);
}