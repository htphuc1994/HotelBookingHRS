package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.domain.entity.Hotel;
import com.hrs.hotelbooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    @GetMapping("/search/{location}")
    public Page<Hotel> searchByLocation(@PathVariable("location") String location,
                                        @RequestParam("page") int pageIndex,
                                        @RequestParam("size") int pageSize) {
        return hotelService.searchHotelByLocation(location, PageRequest.of(pageIndex, pageSize));
    }
}