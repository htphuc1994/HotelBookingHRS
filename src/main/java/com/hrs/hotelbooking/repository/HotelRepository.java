package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.domain.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Page<Hotel> findByLocationContainingIgnoreCase(String location, Pageable pageable);
}