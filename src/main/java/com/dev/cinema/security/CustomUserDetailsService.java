package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByUsername(email);

        UserBuilder builder;
        if (user != null) {
            String[] roles = user.getRoles().stream()
                    .map(Role::getRoleName)
                    .toArray(String[]::new);
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(user.getPassword());
            builder.roles(roles);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

    private User findUserByUsername(String username) {
        Authentication authentication;
        Optional<User> userOptional = userService.findByEmail(username);
        return userOptional.orElse(null);
    }
}
