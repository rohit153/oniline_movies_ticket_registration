package com.online_movie_ticket_reservation_system.omtrs.model.movies;

import com.online_movie_ticket_reservation_system.omtrs.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "MOVIE_ID")
    private long movieId;
    @Column(name = "MOVIE_POSTER_URL")
    private String moviePosterUrl;
    @Column(name = "MOVIE_TAGS")
    private String movieTags;
    @Column(name = "MOVIE_DESC")
    private String movieDescription;

    @OneToOne
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieTags() {
        return movieTags;
    }

    public void setMovieTags(String movieTags) {
        this.movieTags = movieTags;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public void setMoviePosterUrl(String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }
}
