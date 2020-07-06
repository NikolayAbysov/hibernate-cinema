package com.dev.cinema.controller;

import com.dev.cinema.dto.OrderRequestAddDto;
import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.mapper.OrderMapper;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderMapper orderMapper, UserService userService,
                           OrderService orderService,
                           ShoppingCartService shoppingCartService) {
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody @Valid OrderRequestAddDto orderRequestAddDto) {
        User user = userService.findByEmail(orderRequestAddDto.getUserEmail()).get();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(@RequestParam Authentication authentication) {
        User user = userService.findByEmail(authentication.getName()).get();
        List<Order> orders = orderService.getOrderHistory(user);
        return orders.stream()
                .map(orderMapper::map)
                .collect(Collectors
                        .toList());
    }
}
