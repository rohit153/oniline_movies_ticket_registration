package com.online_movie_ticket_reservation_system.omtrs.DataLoader;

import com.online_movie_ticket_reservation_system.omtrs.Enum.Roles;
import com.online_movie_ticket_reservation_system.omtrs.model.Role;
import com.online_movie_ticket_reservation_system.omtrs.model.movies.Movie;
import com.online_movie_ticket_reservation_system.omtrs.repository.MovieRepository;
import com.online_movie_ticket_reservation_system.omtrs.repository.RoleRepository;
import com.online_movie_ticket_reservation_system.omtrs.repository.UserRepository;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Created by rohit  Tamang
 * class has onApplication event  and all the predifined data are store in to data base
 * on 10 Dec, 2022
 */
@Component
public class LoadData implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        saveRole();
//        populateMovieTable();
    }

    /*role save first*/
    @Transactional
    public void saveRole() {
        createRole(Roles.ROLE_ADMIN);
        createRole(Roles.ROLE_USER);
        createRole(Roles.ROLE_MODERATOR);
    }


    private void createRole(Roles role) {
        Optional<Role> roles = roleRepository.findByName(role);
        roles.orElseGet(() ->
                roleRepository.save(Role.builder()
                        .name(role)
                        .build())
        );
    }

    private class ProcessMovie implements Runnable {
        private String movieLine;
        private String linkLine;

        ProcessMovie(String movieLine, String linkLine) {
            this.movieLine = movieLine;
            this.linkLine = linkLine;
        }


        @Override
        public void run() {
            String[] movieInfo = movieLine.split(",");

            String movieName = "";

            for (int i = 1; i < movieInfo.length - 1; i++) {
                if (i == movieInfo.length - 2)
                    movieName += movieInfo[i];
                else
                    movieName += movieInfo[i] + ",";
            }

            Movie movie = new Movie();
            movie.setMovieId(Long.parseLong(movieInfo[0]));
            movie.setMovieName(movieName.substring(0, movieName.indexOf('(')).trim());
            movie.setMovieTags(movieInfo[2]);

            String[] linkInfo = linkLine.split(",");
            Document movieLensPage = null;
            try {
                movieLensPage = Jsoup.connect("https://www.imdb.com/title/tt" + linkInfo[1]).get();
            } catch (HttpStatusException e) {
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (movieLensPage != null) {
                try {
                    String des = movieLensPage.getElementsByClass("sc-16ede01-6").first().children().select("span.sc-16ede01-0").first().ownText();
                    movie.setMovieDescription(des);
                }catch (Exception e){
                    movie.setMovieDescription("description is not available");
                }


                Element image = movieLensPage.getElementsByClass("ipc-poster").first() == null ? null : movieLensPage.getElementsByClass("ipc-poster").first().children().first().children().first();
                movie.setMoviePosterUrl(image == null ? null : image.attr("src"));
            }

            movieRepository.save(movie);
        }
    }

    /*populate the movies in table*/
    private void populateMovieTable() {
        try (BufferedReader brMovies = new BufferedReader(new InputStreamReader(new ClassPathResource("movies.medium.csv").getInputStream()));
             BufferedReader brLinks = new BufferedReader(new InputStreamReader(new ClassPathResource("links.csv").getInputStream()))) {
            String movieLine;
            String linkLine;
            brMovies.readLine();    // Skip header line
            brLinks.readLine();     // Skip header line
            while ((movieLine = brMovies.readLine()) != null) {
                linkLine = brLinks.readLine();
                new ProcessMovie(movieLine, linkLine).run();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
