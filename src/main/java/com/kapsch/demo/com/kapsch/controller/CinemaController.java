package com.kapsch.demo.com.kapsch.controller;

import com.kapsch.demo.com.kapsch.model.Cinema;
import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.service.CinemaService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/v0/api/cinemas")
@RequiredArgsConstructor
public class CinemaController {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_DATE_TIME;

    private final CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<List<String>> getAllCinemas() {
        List<String> result = cinemaService
            .getAllCinemas()
            .stream()
            .map(Cinema::getName)
            .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/withnplusone")
    public ResponseEntity<List<String>> showNPlusOneProblem() {
        List<String> movies = processMovies(cinemaService.getCinema(8L));
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/withjoinfetch")
    public ResponseEntity<List<String>> showJoinFetch() {
        List<String> movies = processMovies(cinemaService.getCinemaJoinFetch(8L));
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/withentitygraph")
    public ResponseEntity<List<String>> showEntityManager() {
        List<String> movies = processMovies(cinemaService.getCinemaEntityGraph(8L));
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    private List<String> processMovies(Cinema cinema) {
        return cinema.getMovies().stream()
            .map(Movie::getName)
            .collect(Collectors.toList());
    }

    @GetMapping("/session")
    public ResponseEntity<Set<String>> getCinemasBySessionFilterDate(
        @RequestParam(value = "fromDate") String from,
        @RequestParam(value = "toDate") String to) {
        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime dateFrom = dateTimeFormat.parse(from, LocalDateTime::from);
        LocalDateTime dateTo = dateTimeFormat.parse(to, LocalDateTime::from);
        Set<String> cinemas = cinemaService.getCinemaBySessionAmountFilterByStartDateAndEndDate(dateFrom, dateTo);
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    @GetMapping("/anothernplusone")
    public ResponseEntity<List<String>> showNPlusOneProblemSessions() {
       List<String> startTime =  cinemaService.getCinema(8L)
            .getMovies().get(0)
            .getSessions()
            .stream()
            .map(session -> session.getStartTime().toString())
            .collect(Collectors.toList());
        return new ResponseEntity<>(startTime, HttpStatus.OK);
    }
}
