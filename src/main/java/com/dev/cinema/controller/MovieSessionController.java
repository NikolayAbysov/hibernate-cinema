package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieSessionRequestAddDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.mapper.MovieSessionMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private final MovieSessionMapper movieSessionMapper;
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionMapper movieSessionMapper, MovieSessionService movieSessionService) {
        this.movieSessionMapper = movieSessionMapper;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping
    public void addMovieSession(@RequestBody @Valid
                                            MovieSessionRequestAddDto movieSessionRequestAddDto) {
        movieSessionService.add(movieSessionMapper.map(movieSessionRequestAddDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessions(@RequestParam Long movieId,
                                                             @RequestParam ("showTime")
                                                             @DateTimeFormat(iso = DateTimeFormat
                                                                     .ISO
                                                                     .DATE_TIME)
                                                                     LocalDate date) {
        List<MovieSession> movieSessions = movieSessionService.findAvailableSessions(movieId, date);
        return movieSessions.stream()
                .map(movieSessionMapper::map)
                .collect(Collectors
                        .toList());
    }
}
