package com.dev.cinema.service;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {

    MovieSession add(MovieSession session);

    MovieSession getSession(Long id);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
