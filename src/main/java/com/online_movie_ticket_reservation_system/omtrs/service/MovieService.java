package com.online_movie_ticket_reservation_system.omtrs.service;

import com.online_movie_ticket_reservation_system.omtrs.model.movies.Movie;

import java.util.List;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
public interface MovieService {
    List<Movie> getMoviesList();

    Movie getMoviesById(Integer id);
}
