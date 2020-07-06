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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectDataController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CinemaHallServiceImpl cinemaHallService;
    private final MovieService movieService;
    private final MovieSessionService movieSessionService;

    public InjectDataController(UserService userService, RoleService roleService,
                                PasswordEncoder passwordEncoder,
                                CinemaHallServiceImpl cinemaHallService,
                                MovieService movieService,
                                MovieSessionService movieSessionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
    }

    @PostConstruct
    public void inject() {
        initMovieSession();
        initRoles();
        initUser();
    }

    private CinemaHall initCinemaHall() {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(150);
        cinemaHall.setDescription("Red hall");
        return cinemaHallService.add(cinemaHall);
    }

    private Movie initMovie() {
        Movie movie = new Movie();
        movie.setTitle("Star Wars");
        movie.setDescription("Wars around stars");
        return movieService.add(movie);
    }

    private void initMovieSession() {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(initCinemaHall());
        movieSession.setMovie(initMovie());
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);
    }

    private void initRoles() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));
    }

    private void initUser() {
        User user = new User();
        user.setEmail("shion@ukr.net");
        user.setPassword(passwordEncoder.encode("123"));
        user.setRoles(Set.of(roleService.getRole("USER")));
        userService.add(user);
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(Set.of(roleService.getRole("ADMIN")));
        userService.add(admin);
    }
}
