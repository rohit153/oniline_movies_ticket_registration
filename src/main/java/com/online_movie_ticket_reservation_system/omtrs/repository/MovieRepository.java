package com.online_movie_ticket_reservation_system.omtrs.repository;

import com.online_movie_ticket_reservation_system.omtrs.model.movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieName(String movieName);
    @Query(value = "select * from movie where id=?1",nativeQuery = true)
    Movie findByMovieId(long movieId);
}
