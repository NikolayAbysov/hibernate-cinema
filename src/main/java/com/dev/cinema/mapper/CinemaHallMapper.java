package com.dev.cinema.mapper;

import com.dev.cinema.dto.CinemaHallRequestAddDto;
import com.dev.cinema.dto.CinemaHallResponseDto;
import com.dev.cinema.model.CinemaHall;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHallResponseDto map(CinemaHall cinemaHall) {
        CinemaHallResponseDto hallResponseDto = new CinemaHallResponseDto();
        hallResponseDto.setCapacity(cinemaHall.getCapacity());
        hallResponseDto.setDescription(cinemaHall.getDescription());
        hallResponseDto.setId(cinemaHall.getId());
        return hallResponseDto;
    }

    public CinemaHall map(CinemaHallRequestAddDto cinemaHallRequestAddDto) {
        CinemaHall hall = new CinemaHall();
        hall.setDescription(cinemaHallRequestAddDto.getDescription());
        hall.setCapacity(cinemaHallRequestAddDto.getCapacity());
        return hall;
    }
}
