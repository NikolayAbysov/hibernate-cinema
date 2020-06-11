package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieSessionRequestAddDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.mapper.ModelMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MovieSessionService movieSessionService;

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestAddDto movieSessionRequestAddDto) {
        movieSessionService.add(modelMapper.map(movieSessionRequestAddDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessions(@RequestParam Long movieId,
                                                             @RequestParam ("showTime")
                                                             @DateTimeFormat(iso = DateTimeFormat
                                                                     .ISO
                                                                     .DATE_TIME)
                                                                     LocalDate date) {
        List<MovieSessionResponseDto> list = new ArrayList<>();
        List<MovieSession> movieSessions = movieSessionService.findAvailableSessions(movieId, date);
        for (MovieSession movieSession : movieSessions) {
            list.add(modelMapper.map(movieSession));
        }
        return list;
    }
}
