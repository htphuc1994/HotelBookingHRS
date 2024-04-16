package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Custom database queries can be defined here
}
