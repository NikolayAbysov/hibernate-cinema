package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieDetailsResponseDto;
import com.dev.cinema.dto.MovieRequestAddDto;
import com.dev.cinema.mapper.ModelMapper;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import java.util.ArrayList;
import java.util.List;
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
    private ModelMapper modelMapper;
    @Autowired
    private MovieService movieService;

    @PostMapping
    public void addMovie(@RequestBody MovieRequestAddDto movieRequestAddDto) {
        movieService.add(modelMapper.map(movieRequestAddDto));
    }

    @GetMapping
    public List<MovieDetailsResponseDto> getMovies() {
        List<MovieDetailsResponseDto> list = new ArrayList<>();
        List<Movie> movies = movieService.getAll();
        for (Movie movie : movies) {
            list.add(modelMapper.map(movie));
        }
        return list;
    }
}
