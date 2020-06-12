package com.dev.cinema.mapper;

import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto map(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setId(shoppingCart.getId());
        cartResponseDto.setOrderDate(shoppingCart.getOrderDate());
        cartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        List<Ticket> tickets = shoppingCart.getTickets();
        List<TicketResponseDto> list = tickets.stream()
                .map(this::map)
                .collect(Collectors
                        .toList());
        cartResponseDto.setTickets(list);
        return cartResponseDto;
    }

    private TicketResponseDto map(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setCinemaHallDescription(ticket
                .getMovieSession()
                .getCinemaHall()
                .getDescription());
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setMovieShowTime(ticket
                .getMovieSession()
                .getShowTime());
        ticketResponseDto.setMovieTitle(ticket
                .getMovieSession()
                .getMovie()
                .getTitle());
        return ticketResponseDto;
    }
}
