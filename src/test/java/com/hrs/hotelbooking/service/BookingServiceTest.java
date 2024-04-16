package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.domain.entity.Booking;
import com.hrs.hotelbooking.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class BookingServiceTest {
    @InjectMocks
    private BookingService bookingService;
    @Mock
    private BookingRepository bookingRepository;
    private Booking booking;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        // Extract common setup to @BeforeEach method
        booking = new Booking();
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        doNothing().when(bookingRepository).deleteById(anyLong());
    }

    @Test
    void testCreateBooking() {
        Booking createdBooking = bookingService.createBooking(booking);
        assertNotNull(createdBooking);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void testGetBookingById() {
        Booking retrievedBooking = bookingService.getBookingById(1L);
        assertNotNull(retrievedBooking);
        verify(bookingRepository).findById(anyLong());
    }

    @Test
    void testUpdateBooking() {
        Booking updatedBooking = bookingService.updateBooking(booking);
        assertNotNull(updatedBooking);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void testDeleteBooking() {
        bookingService.deleteBooking(1L);
        verify(bookingRepository).deleteById(anyLong());
    }
}