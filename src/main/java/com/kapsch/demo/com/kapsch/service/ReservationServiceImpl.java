package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.controller.RequestDto;
import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.model.Session;
import com.kapsch.demo.com.kapsch.model.Ticket;
import java.beans.Transient;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ServiceFacade {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SessionService sessionService;


    @Override
    public void saveRelation(RequestDto request) {

        Ticket ticket = ticketService.persist(request.getTicketPrice(), request.getTicketClient());
        Session session = sessionService.persist(request.getSessionStartTime(), request.getSessionEndTime());
        Movie movie = movieService.persist(request.getMovieName(), request.getMovieDuration());

        session.setTickets(new HashSet<>(Arrays.asList(ticket)));
//        movie.setSessions(new HashSet<>(Arrays.asList(session)));

        cinemaService.persist(
            request.getMovieName(),
            request.getCinemaAddress(),
            new HashSet<>(Arrays.asList(movie))
        );

    }
}
