package com.kapsch.demo.com.kapsch.controller;

import com.kapsch.demo.com.kapsch.model.Cinema;
import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.repository.CinemaRepository;
import com.kapsch.demo.com.kapsch.service.ServiceFacade;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CinemaController {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ServiceFacade serviceFacade;

    @GetMapping("/cinemas")
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }


    @GetMapping("/cinemas/{id}")
    public Set<String> getAllMoviesByCinema(@PathVariable(name="id", required = true) Long id) {
        return cinemaRepository.getById(id).stream()
            .map(Movie::getName)
            .collect(Collectors.toSet());
    }

    @GetMapping("/cinemas/{fromDate}/{toDate}")
    public Set<String> getAllMoviesByCinema(
        @PathVariable(name="fromDate", required = true) String from,
        @PathVariable(name="toDate", required = true) String to) {

        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime dateFrom = dateTimeFormat.parse(from, LocalDateTime::from);
        LocalDateTime dateTo = dateTimeFormat.parse(to, LocalDateTime::from);

        return cinemaRepository.getCinemaNamesWithMoreThanFiveSessions(dateFrom, dateTo);
    }

    @GetMapping("/creator")
    public void creator() {

        //Controller  -> Facade -> Service(Transactional) -> Repository

        RequestDto requestDto = RequestDto.builder()
            .ticketPrice(45.0)
            .ticketClient("Petro")
            .cinemaName("Hulliver")
            .cinemaAddress("Veluka Vasulkivskia")
            .movieName("Hulk")
            .movieDuration(new Timestamp(new Date().getTime()))
            .sessionEndTime(new Date())
            .sessionStartTime(new Date())
            .build();
        serviceFacade.saveRelation(requestDto);
    }

}
