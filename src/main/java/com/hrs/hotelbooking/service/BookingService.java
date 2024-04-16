package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.domain.entity.Booking;
import com.hrs.hotelbooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private static final String BOOKING_NOT_FOUND_MESSAGE = "No Booking with given id found";

    public Mono<Booking> createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Mono<Booking> getBookingById(Long id) {
        return bookingRepository.findById(String.valueOf(id))
                .switchIfEmpty(Mono.error(() -> new RuntimeException(BOOKING_NOT_FOUND_MESSAGE)));
    }

    public Mono<Booking> updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Mono<Void> deleteBooking(Long id) {
        return bookingRepository.deleteById(String.valueOf(id));
    }
}