package com.dev.cinema.dto;

import java.time.LocalDateTime;

public class TicketResponseDto {
    private Long id;
    private String movieTitle;
    private String cinemaHallDescription;
    private LocalDateTime movieShowTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCinemaHallDescription() {
        return cinemaHallDescription;
    }

    public void setCinemaHallDescription(String cinemaHallDescription) {
        this.cinemaHallDescription = cinemaHallDescription;
    }

    public LocalDateTime getMovieShowTime() {
        return movieShowTime;
    }

    public void setMovieShowTime(LocalDateTime movieShowTime) {
        this.movieShowTime = movieShowTime;
    }
}
