package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.domain.entity.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
    Mono<Booking> findByBookingNumber(String bookingNumber);
}