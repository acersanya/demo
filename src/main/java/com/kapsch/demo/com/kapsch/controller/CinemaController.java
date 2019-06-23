package com.kapsch.demo.com.kapsch.controller;

import com.kapsch.demo.com.kapsch.service.ReservationService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/api")
public class CinemaController {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private ReservationService reservationService;

//    @GetMapping("/cinemas")
//    public List<Cinema> getAllCinemas() {
//        return cinemaRepository.findAll();
//    }

    @GetMapping("/cinemas/{id}")
    public Set<String> getAllMoviesByCinema(@PathVariable(name = "id", required = true) Long id) {
        return reservationService.getMoviesByCinemaId(id);
    }

    @GetMapping("/cinemas/{fromDate}/{toDate}")
    public Set<String> getAllMoviesByCinema(
        @PathVariable(name = "fromDate", required = true) String from,
        @PathVariable(name = "toDate", required = true) String to) {
        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime dateFrom = dateTimeFormat.parse(from, LocalDateTime::from);
        LocalDateTime dateTo = dateTimeFormat.parse(to, LocalDateTime::from);
        return reservationService.getAllMoviesByCinemaFilterByStartDateAndEndDate(dateFrom, dateTo);
    }
}
