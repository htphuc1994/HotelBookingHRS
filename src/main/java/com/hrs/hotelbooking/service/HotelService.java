package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.domain.entity.Hotel;
import com.hrs.hotelbooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public Mono<Hotel> createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Flux<Hotel> searchHotelByLocation(String location, Pageable pageable) {
        return hotelRepository.findByLocationContainingIgnoreCase(location, pageable);
    }
}