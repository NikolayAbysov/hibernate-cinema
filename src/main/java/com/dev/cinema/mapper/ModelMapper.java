package com.dev.cinema.mapper;

import com.dev.cinema.dto.CinemaHallRequestAddDto;
import com.dev.cinema.dto.CinemaHallResponseDto;
import com.dev.cinema.dto.MovieDetailsResponseDto;
import com.dev.cinema.dto.MovieRequestAddDto;
import com.dev.cinema.dto.MovieSessionRequestAddDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.dto.UserRegisterDto;
import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;

    public MovieDetailsResponseDto map(Movie movie) {
        MovieDetailsResponseDto movieDetailsResponseDto = new MovieDetailsResponseDto();
        movieDetailsResponseDto.setId(movie.getId());
        movieDetailsResponseDto.setDescription(movie.getDescription());
        movieDetailsResponseDto.setTitle(movie.getTitle());
        return movieDetailsResponseDto;
    }

    public Movie map(MovieRequestAddDto movieRequestAddDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestAddDto.getTitle());
        movie.setDescription(movieRequestAddDto.getDescription());
        return movie;
    }

    public CinemaHallResponseDto map(CinemaHall cinemaHall) {
        CinemaHallResponseDto hallResponseDto = new CinemaHallResponseDto();
        hallResponseDto.setCapacity(cinemaHall.getCapacity());
        hallResponseDto.setDescription(cinemaHall.getDescription());
        hallResponseDto.setId(cinemaHall.getId());
        return hallResponseDto;
    }

    public CinemaHall map(CinemaHallRequestAddDto cinemaHallRequestAddDto) {
        CinemaHall hall = new CinemaHall();
        hall.setDescription(cinemaHallRequestAddDto.getDescription());
        hall.setCapacity(cinemaHallRequestAddDto.getCapacity());
        return hall;
    }

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

    public MovieSession map(MovieSessionRequestAddDto movieSessionRequestAddDto) {
        Movie movie = movieService.getMovie(movieSessionRequestAddDto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService.getCinemaHall(movieSessionRequestAddDto
                .getCinemaHallId());
        MovieSession session = new MovieSession();
        session.setMovie(movie);
        session.setCinemaHall(cinemaHall);
        session.setShowTime(movieSessionRequestAddDto.getShowTime());
        return session;
    }

    public MovieSessionResponseDto map(MovieSession movieSession) {
        MovieSessionResponseDto sessionResponseDto = new MovieSessionResponseDto();
        sessionResponseDto.setCinemaHallDescription(movieSession
                .getCinemaHall()
                .getDescription());
        sessionResponseDto.setCinemaHallId(movieSession
                .getCinemaHall()
                .getId());
        sessionResponseDto.setSessionId(movieSession.getId());
        sessionResponseDto.setLocalDate(movieSession
                .getShowTime()
                .toLocalDate());
        sessionResponseDto.setMovieTitle(movieSession
                .getMovie()
                .getTitle());
        return sessionResponseDto;
    }

    public ShoppingCartResponseDto map(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setId(shoppingCart.getId());
        cartResponseDto.setOrderDate(shoppingCart.getOrderDate());
        cartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        List<TicketResponseDto> list = new ArrayList<>();
        List<Ticket> tickets = shoppingCart.getTickets();
        for (Ticket ticket : tickets) {
            list.add(map(ticket));
        }
        cartResponseDto.setTickets(list);
        return cartResponseDto;
    }

    public OrderResponseDto map(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setUserEmail(order.getUser().getEmail());

        List<TicketResponseDto> list = new ArrayList<>();
        List<Ticket> tickets = order.getTickets();
        for (Ticket ticket : tickets) {
            list.add(map(ticket));
        }

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
