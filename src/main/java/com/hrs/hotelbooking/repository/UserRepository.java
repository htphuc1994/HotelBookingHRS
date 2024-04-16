package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.domain.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    // Custom database queries can be defined here
}