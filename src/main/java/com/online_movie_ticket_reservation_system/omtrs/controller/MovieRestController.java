package com.online_movie_ticket_reservation_system.omtrs.controller;

import com.online_movie_ticket_reservation_system.omtrs.Pojo.SignupRequest;
import com.online_movie_ticket_reservation_system.omtrs.Pojo.response.GlobalResponseApi;
import com.online_movie_ticket_reservation_system.omtrs.model.User;
import com.online_movie_ticket_reservation_system.omtrs.model.movies.Movie;
import com.online_movie_ticket_reservation_system.omtrs.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class MovieRestController extends GlobalResponseApi {
    @Autowired
    private MovieService movieService;

    @GetMapping("/get-user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GlobalResponseApi> getMoviesListBookedAndNotBook() {
        try {
            List<Movie> movieList = movieService.getMoviesList();

            return ResponseEntity.ok(successResponse("fetch successFully !", movieList));
        } catch (Exception e) {
            return ResponseEntity.ok(errorResponse("Errror : ", e.getMessage()));
        }
    }

    @GetMapping("/movies/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GlobalResponseApi> getMoviesById(@PathVariable Integer id) {
        try {
            Movie movie = movieService.getMoviesById(id);

            return ResponseEntity.ok(successResponse("fetch successFully !", movie));
        } catch (Exception e) {
            return ResponseEntity.ok(errorResponse("Errror : ", e.getMessage()));
        }
    }

}
