package com.dev.cinema.service;

import com.dev.cinema.model.User;
import java.util.Optional;

public interface UserService {

    User add(User user);

    User getUser(Long id);

    Optional<User> findByEmail(String email);
}
