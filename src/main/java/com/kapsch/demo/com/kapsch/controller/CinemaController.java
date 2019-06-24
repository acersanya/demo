package com.kapsch.demo.com.kapsch.controller;

import com.kapsch.demo.com.kapsch.model.Cinema;
import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.service.ReservationService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/v0/api")
@RequiredArgsConstructor
public class CinemaController {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_DATE_TIME;

    private final ReservationService reservationService;

    @GetMapping("/cinemas")
    public List<String> getAllCinemas() {
        return reservationService
            .getAllCinemas()
            .stream()
            .map(Cinema::getName)
            .collect(Collectors.toList());
    }

    @GetMapping("/cinemas/withnplusone")
    public List<String> showNPlusOneProblem() {
        return reservationService.getCinema(8L)
            .getMovies()
            .stream()
            .map(Movie::getName)
            .collect(Collectors.toList());
    }

    @GetMapping("/cinemas/withjoinfetch")
    public List<String> showJoinFetch() {
        return reservationService.getCinemaJoinFetch(8L)
            .getMovies()
            .stream()
            .map(Movie::getName)
            .collect(Collectors.toList());
    }

    @GetMapping("/cinemas/withentitygraph")
    public List<String> showEntityManager() {
        return reservationService.getCinemaEntityGraph(8L)
            .getMovies()
            .stream()
            .map(Movie::getName)
            .collect(Collectors.toList());
    }

    @GetMapping("/cinemas/session")
    public Set<String> getCinemasBySessionFilterDate(
        @RequestParam(value = "fromDate") String from,
        @RequestParam(value = "toDate") String to) {
        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime dateFrom = dateTimeFormat.parse(from, LocalDateTime::from);
        LocalDateTime dateTo = dateTimeFormat.parse(to, LocalDateTime::from);
        return reservationService.getCinemaBySessionAmountFilterByStartDateAndEndDate(dateFrom, dateTo);
    }

    @GetMapping("/cinemas/anothernplusone")
    public List<String> showNPlusOneProblemSessions() {
        return reservationService.getCinema(8L)
            .getMovies().get(0)
            .getSessions()
            .stream()
            .map(session -> session.getStartTime().toString())
            .collect(Collectors.toList());
    }
}
