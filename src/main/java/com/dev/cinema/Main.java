package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static final Injector INJECTOR
            = Injector.getInstance("com.dev.cinema");
    private static final MovieService movieService
            = (MovieService) INJECTOR.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService
            = (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService
            = (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
    private static final AuthenticationService authenticationService
            = (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
    private static final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDescription("Description");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(150);
        cinemaHall.setDescription("Desc");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L, LocalDate.now())
                .forEach(System.out::println);

        User user = new User();
        user.setEmail("kkkkk");
        user.setPassword("123");
        authenticationService.register("kkkkk","123");
        authenticationService.login("kkkkk","123");
        //authenticationService.register("kkkkk","123");
        System.out.println(userService.findByEmail("kkkkk").get());
    }
}
