package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.repository.CinemaRepository;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationReservationServiceImpl implements ReservationService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public Set<String> getMoviesByCinemaId(Long id) {
        return cinemaRepository.getById(id).stream()
            .map(Movie::getName)
            .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllMoviesByCinemaFilterByStartDateAndEndDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return cinemaRepository.getCinemaNamesWithMoreThanFiveSessions(dateFrom, dateTo);
    }
}
