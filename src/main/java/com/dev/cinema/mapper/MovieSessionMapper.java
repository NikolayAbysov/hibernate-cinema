package com.dev.cinema.mapper;

import com.dev.cinema.dto.MovieSessionRequestAddDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;

    public MovieSession map(MovieSessionRequestAddDto movieSessionRequestAddDto) {
        Movie movie = movieService.getMovie(movieSessionRequestAddDto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService.getCinemaHall(movieSessionRequestAddDto
                .getCinemaHallId());
        MovieSession session = new MovieSession();
        session.setMovie(movie);
        session.setCinemaHall(cinemaHall);
        session.setShowTime(movieSessionRequestAddDto.getShowTime());
        return session;
    }

    public MovieSessionResponseDto map(MovieSession movieSession) {
        MovieSessionResponseDto sessionResponseDto = new MovieSessionResponseDto();
        sessionResponseDto.setCinemaHallDescription(movieSession
                .getCinemaHall()
                .getDescription());
        sessionResponseDto.setCinemaHallId(movieSession
                .getCinemaHall()
                .getId());
        sessionResponseDto.setSessionId(movieSession.getId());
        sessionResponseDto.setLocalDate(movieSession
                .getShowTime()
                .toLocalDate());
        sessionResponseDto.setMovieTitle(movieSession
                .getMovie()
                .getTitle());
        return sessionResponseDto;
    }
}
