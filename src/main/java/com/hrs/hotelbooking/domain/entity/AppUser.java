package com.hrs.hotelbooking.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Set;

@Document(collection = "app_user")
@Getter
@Setter
@RequiredArgsConstructor
public class AppUser {

    @Id
    private Long id;

    @DBRef
    private Set<Booking> bookings;

    // Remaining code
    private String name;
    private String email;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return getId() != null && getId().equals(appUser.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ")";
    }
}