package com.dev.cinema.controller;

import com.dev.cinema.dto.UserRegisterDto;
import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = userMapper.map(userRegisterDto);
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
