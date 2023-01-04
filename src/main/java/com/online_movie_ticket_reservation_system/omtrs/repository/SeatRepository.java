package com.online_movie_ticket_reservation_system.omtrs.repository;

import com.online_movie_ticket_reservation_system.omtrs.model.movies.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rohit  Tamang
 * on 11 Dec, 2022
 */
public interface SeatRepository extends JpaRepository<Seat,Integer> {
}
