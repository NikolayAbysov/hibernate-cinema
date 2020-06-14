package com.dev.cinema.mapper;

import com.dev.cinema.dto.UserRegisterDto;
import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto map(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setId(user.getId());
        return responseDto;
    }

    public User map(UserRegisterDto userRegisterDto) {
        User usr = new User();
        usr.setEmail(userRegisterDto.getEmail());
        usr.setPassword(userRegisterDto.getPassword());
        return usr;
    }
}
