package com.hrs.hotelbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.hotelbooking.domain.entity.AppUser;
import com.hrs.hotelbooking.domain.entity.Booking;
import com.hrs.hotelbooking.domain.entity.Hotel;
import com.hrs.hotelbooking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookingController.class)
class BookingControllerTest {

    private static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private BookingService bookingService;

    @Test
    void createBookingTest() throws JsonProcessingException {
        Booking booking = initBooking();
        String bookingJson = convertBookingToJson(booking);

        when(bookingService.createBooking(booking)).thenReturn(booking);

        webTestClient.post()
                .uri("/bookings")
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE.toString())
                .body(BodyInserters.fromValue(bookingJson))
                .exchange()
                .expectStatus().isOk();

        verify(bookingService, times(1)).createBooking(booking);
    }

    @Test
    void getBookingTest() {
        Long id = 1L;
        Booking booking = initBooking();

        when(bookingService.getBookingById(id)).thenReturn(booking);

        webTestClient.get()
                .uri("/bookings/" + id)
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE.toString())
                .exchange()
                .expectStatus().isOk();

        verify(bookingService, times(1)).getBookingById(id);
    }

    // Helper methods for tests
    private Booking initBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setUser(new AppUser());
        booking.setHotel(new Hotel());
        return booking;
    }

    private String convertBookingToJson(Booking booking) throws JsonProcessingException {
        return objectMapper.writeValueAsString(booking);
    }
}