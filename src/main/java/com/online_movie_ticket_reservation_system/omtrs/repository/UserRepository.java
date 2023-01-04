package com.online_movie_ticket_reservation_system.omtrs.repository;

import com.online_movie_ticket_reservation_system.omtrs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
