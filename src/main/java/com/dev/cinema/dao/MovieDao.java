package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieDao {

    Movie add(Movie movie);

    Movie getMovie(Long id);

    List<Movie> getAll();
}
