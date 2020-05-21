package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDescription("Description");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
