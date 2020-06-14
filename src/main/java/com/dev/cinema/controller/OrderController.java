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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestAddDto orderRequestAddDto) {
        User user = userService.findByEmail(orderRequestAddDto.getUserEmail()).get();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(@RequestParam Long userId) {
        User user = userService.getUser(userId);
        List<Order> orders = orderService.getOrderHistory(user);
        return orders.stream()
                .map(o -> orderMapper
                        .map(o))
                .collect(Collectors
                        .toList());
    }
}
