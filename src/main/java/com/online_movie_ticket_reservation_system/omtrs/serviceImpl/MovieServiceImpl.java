package com.online_movie_ticket_reservation_system.omtrs.serviceImpl;

import com.online_movie_ticket_reservation_system.omtrs.model.movies.Movie;
import com.online_movie_ticket_reservation_system.omtrs.repository.MovieRepository;
import com.online_movie_ticket_reservation_system.omtrs.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMoviesList() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMoviesById(Integer id) {
        return movieRepository.findByMovieId(id);
    }
}
