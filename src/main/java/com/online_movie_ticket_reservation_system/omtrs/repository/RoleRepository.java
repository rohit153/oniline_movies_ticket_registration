package com.online_movie_ticket_reservation_system.omtrs.repository;

import com.online_movie_ticket_reservation_system.omtrs.Enum.Roles;
import com.online_movie_ticket_reservation_system.omtrs.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Roles roleUser);


}
