package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieDetailsResponseDto;
import com.dev.cinema.dto.MovieRequestAddDto;
import com.dev.cinema.mapper.MovieMapper;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieService movieService;

    @PostMapping
    public void addMovie(@RequestBody MovieRequestAddDto movieRequestAddDto) {
        movieService.add(movieMapper.map(movieRequestAddDto));
    }

    @GetMapping
    public List<MovieDetailsResponseDto> getMovies() {
        List<Movie> movies = movieService.getAll();
        return movies.stream()
                .map(m -> movieMapper
                        .map(m))
                .collect(Collectors
                        .toList());
    }
}
