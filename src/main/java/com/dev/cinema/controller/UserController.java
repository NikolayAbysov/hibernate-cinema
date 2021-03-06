package com.dev.cinema.controller;

import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.exception.NoUserException;
import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/byemail")
    public UserResponseDto getUserByEmail(@RequestParam Authentication authentication) {
        Optional<User> userOptional = userService.findByEmail(authentication.getName());
        User user;
        if (userOptional.isEmpty()) {
            throw new NoUserException("User with email: " + authentication.getName() + "not found");
        } else {
            user = userOptional.get();
        }
        return userMapper.map(user);
    }
}
