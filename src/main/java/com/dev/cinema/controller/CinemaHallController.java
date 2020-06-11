package com.dev.cinema.controller;

import com.dev.cinema.dto.CinemaHallRequestAddDto;
import com.dev.cinema.dto.CinemaHallResponseDto;
import com.dev.cinema.mapper.ModelMapper;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CinemaHallService cinemaHallService;

    @PostMapping
    public void addCinemaHall(@RequestBody CinemaHallRequestAddDto cinemaHallRequestAddDto) {
        cinemaHallService.add(modelMapper.map(cinemaHallRequestAddDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        List<CinemaHallResponseDto> list = new ArrayList<>();
        List<CinemaHall> cinemaHalls = cinemaHallService.getAll();
        for (CinemaHall cinemaHall : cinemaHalls) {
            list.add(modelMapper.map(cinemaHall));
        }
        return list;
    }
}
