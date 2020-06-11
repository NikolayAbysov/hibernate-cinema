package com.dev.cinema.service;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieService {

    Movie add(Movie movie);

    Movie getMovie(Long id);

    List<Movie> getAll();
}
