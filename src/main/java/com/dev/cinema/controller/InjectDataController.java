package com.dev.cinema.controller;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.impl.CinemaHallServiceImpl;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectDataController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CinemaHallServiceImpl cinemaHallService;
    @Autowired
    MovieService movieService;
    @Autowired
    MovieSessionService movieSessionService;
    @Autowired
    OrderService orderService;
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/inject")
    public void inject() {

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(150);
        cinemaHall.setDescription("Red hall");
        cinemaHallService.add(cinemaHall);

        Movie movie = new Movie();
        movie.setTitle("Star Wars");
        movie.setDescription("Wars around stars");
        movieService.add(movie);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);

        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));

        User user = new User();
        user.setEmail("shion");
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
