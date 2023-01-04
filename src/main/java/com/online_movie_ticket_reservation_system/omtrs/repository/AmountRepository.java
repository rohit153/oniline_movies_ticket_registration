package com.online_movie_ticket_reservation_system.omtrs.repository;

import com.online_movie_ticket_reservation_system.omtrs.model.movies.TotalAmount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rohit  Tamang
 * on 11 Dec, 2022
 */
public interface AmountRepository extends JpaRepository<TotalAmount,Integer> {
}
