package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.domain.entity.Booking;
import com.hrs.hotelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Booking get(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // Additional endpoints for update and delete
}
