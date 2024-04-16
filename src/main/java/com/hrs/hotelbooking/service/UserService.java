package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.domain.entity.AppUser;
import com.hrs.hotelbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }
}
