package com.dev.cinema.controller;

import com.dev.cinema.dto.UserRegisterDto;
import com.dev.cinema.mapper.ModelMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto);
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
