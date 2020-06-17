package com.dev.cinema.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class MovieSessionRequestAddDto {
    @NotNull(message = "Empty movieId field!")
    private Long movieId;
    @NotNull(message = "Empty cinemaHallId field!")
    private Long cinemaHallId;
    @NotNull(message = "Empty showTime field!")
    private LocalDateTime showTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
