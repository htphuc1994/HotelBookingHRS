package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.domain.entity.AppUser;
import com.hrs.hotelbooking.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public AppUser create(@RequestBody AppUser user) {
        return userService.createUser(user);
    }

}
