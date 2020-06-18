package com.dev.cinema.controller;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.impl.CinemaHallServiceImpl;
import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectDataController {

    private CinemaHall cinemaHall;
    private Movie movie;
    private MovieSession movieSession;
    private User user;
    private User admin;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CinemaHallServiceImpl cinemaHallService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieSessionService movieSessionService;

    public InjectDataController() {
        this.cinemaHall = new CinemaHall();
        this.movie = new Movie();
        this.movieSession = new MovieSession();
        this.user = new User();
        this.admin = new User();
    }

    @PostConstruct
    public void inject() {
        initCinemaHall();
        initMovie();
        initMovieSession();
        initRoles();
        initUser();
    }

    private void initCinemaHall() {
        cinemaHall.setCapacity(150);
        cinemaHall.setDescription("Red hall");
        cinemaHallService.add(cinemaHall);
    }

    private void initMovie() {
        movie.setTitle("Star Wars");
        movie.setDescription("Wars around stars");
        movieService.add(movie);
    }

    private void initMovieSession() {
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);
    }

    private void initRoles() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));
    }

    private void initUser() {
        user.setEmail("shion");
        user.setPassword(passwordEncoder.encode("123"));
        user.setRoles(Set.of(roleService.getRole("USER")));
        userService.add(user);

        admin.setEmail("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(Set.of(roleService.getRole("ADMIN")));
        userService.add(admin);
    }
}
