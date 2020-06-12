package com.dev.cinema.mapper;

import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto map(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setUserEmail(order.getUser().getEmail());
        List<Ticket> tickets = order.getTickets();
        List<TicketResponseDto> list = tickets.stream()
                .map(this::map)
                .collect(Collectors
                        .toList());
        responseDto.setTickets(list);
        return responseDto;
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
