package com.dev.cinema.security.impl;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.anno.Inject;
import com.dev.cinema.lib.anno.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email).orElseThrow(()
                -> new AuthenticationException("No such user"));
        String hashPassword = HashUtil.hashPassword(password, user.getSalt());
        if (user.getPassword().equals(hashPassword)) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String password) {
        if (userService.findByEmail(email).isPresent()) {
            throw new AuthenticationException("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        byte[] salt = HashUtil.getSalt();
        user.setPassword(HashUtil.hashPassword(password, salt));
        user.setSalt(salt);
        return userService.add(user);
    }
}
