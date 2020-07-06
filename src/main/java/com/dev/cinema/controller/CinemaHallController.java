package com.dev.cinema.controller;

import com.dev.cinema.dto.CinemaHallRequestAddDto;
import com.dev.cinema.dto.CinemaHallResponseDto;
import com.dev.cinema.mapper.CinemaHallMapper;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallMapper cinemaHallMapper;
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallMapper cinemaHallMapper,
                                CinemaHallService cinemaHallService) {
        this.cinemaHallMapper = cinemaHallMapper;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestAddDto cinemaHallRequestAddDto) {
        cinemaHallService.add(cinemaHallMapper.map(cinemaHallRequestAddDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        List<CinemaHall> cinemaHalls = cinemaHallService.getAll();
        return cinemaHalls.stream()
                .map(cinemaHallMapper::map)
                .collect(Collectors
                        .toList());
    }
}
