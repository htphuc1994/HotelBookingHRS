package com.hrs.hotelbooking.controller;


import com.hrs.hotelbooking.domain.entity.Hotel;
import com.hrs.hotelbooking.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = HotelController.class)
class HotelControllerTest {

    private static final String HOTELS_URI = "/hotels";
    private static final String SEARCH_URI_TEMPLATE = HOTELS_URI + "/search/{location}";

    private Hotel hotel;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.setId(1L);
    }

    @Test
    void testCreateHotel() {
        when(hotelService.createHotel(any(Hotel.class))).thenReturn(hotel);

        webTestClient.post().uri(HOTELS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(hotel), Hotel.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class).isEqualTo(hotel);
    }

    @Test
    void searchByLocationTest() {
        String location = "location";
        int pageIndex = 0;
        int pageSize = 10;
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Hotel> page = new PageImpl<>(Collections.singletonList(hotel), pageable, 1);

        when(hotelService.searchHotelByLocation(location, pageable)).thenReturn(page);

        String searchUri = UriComponentsBuilder.fromUriString(SEARCH_URI_TEMPLATE)
                .buildAndExpand(Collections.singletonMap("location", location))
                .toUriString();

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(searchUri)
                                .queryParam("page", pageIndex)
                                .queryParam("size", pageSize)
                                .build())
                .exchange()
                .expectStatus().isOk();

        verify(hotelService, times(1)).searchHotelByLocation(location, pageable);
    }
}