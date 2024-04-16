package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.domain.entity.Hotel;
import com.hrs.hotelbooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Page<Hotel> searchHotelByLocation(String location, Pageable pageable) {
        return hotelRepository.findByLocationContainingIgnoreCase(location, pageable);
    }
}
