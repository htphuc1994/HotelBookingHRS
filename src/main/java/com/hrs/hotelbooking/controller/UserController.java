package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.domain.entity.AppUser;
import com.hrs.hotelbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Mono<AppUser> create(@RequestBody Mono<AppUser> user) {
        return user.flatMap(userService::createUser);
    }

}