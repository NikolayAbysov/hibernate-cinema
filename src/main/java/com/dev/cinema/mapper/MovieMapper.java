package com.dev.cinema.mapper;

import com.dev.cinema.dto.MovieDetailsResponseDto;
import com.dev.cinema.dto.MovieRequestAddDto;
import com.dev.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDetailsResponseDto map(Movie movie) {
        MovieDetailsResponseDto movieDetailsResponseDto = new MovieDetailsResponseDto();
        movieDetailsResponseDto.setId(movie.getId());
        movieDetailsResponseDto.setDescription(movie.getDescription());
        movieDetailsResponseDto.setTitle(movie.getTitle());
        return movieDetailsResponseDto;
    }

    public Movie map(MovieRequestAddDto movieRequestAddDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestAddDto.getTitle());
        movie.setDescription(movieRequestAddDto.getDescription());
        return movie;
    }
}
