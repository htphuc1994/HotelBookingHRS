package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.domain.entity.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface HotelRepository extends ReactiveMongoRepository<Hotel, Long> {
    Flux<Hotel> findByLocationContainingIgnoreCase(String location, Pageable pageable);
}