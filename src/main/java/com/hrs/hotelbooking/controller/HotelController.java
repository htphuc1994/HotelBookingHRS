package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.domain.entity.Hotel;
import com.hrs.hotelbooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public Mono<Hotel> create(@RequestBody Mono<Hotel> hotel) {
        return hotel.flatMap(hotelService::createHotel);
    }

    @GetMapping("/search/{location}")
    public Flux<Hotel> searchByLocation(@PathVariable("location") String location,
                                        @RequestParam("page") int pageIndex,
                                        @RequestParam("size") int pageSize) {
        return hotelService.searchHotelByLocation(location, PageRequest.of(pageIndex, pageSize));
    }
}