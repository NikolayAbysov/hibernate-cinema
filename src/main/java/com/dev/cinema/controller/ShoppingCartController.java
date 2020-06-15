package com.dev.cinema.controller;

import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.mapper.ShoppingCartMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @PostMapping("/addmoviesession")
    public void addMovieSession(@RequestParam Long movieSessionId,
                                @RequestParam Authentication authentication) {
        User user = userService.findByEmail(authentication.getName()).get();
        MovieSession movieSession = movieSessionService.getSession(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getByUserId(@RequestParam Authentication authentication) {
        User user = userService.findByEmail(authentication.getName()).get();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.map(shoppingCart);
    }
}
