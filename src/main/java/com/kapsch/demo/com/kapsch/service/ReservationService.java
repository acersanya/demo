package com.kapsch.demo.com.kapsch.service;

import java.time.LocalDateTime;
import java.util.Set;

public interface ReservationService {

    Set<String> getMoviesByCinemaId(Long id);

    Set<String> getAllMoviesByCinemaFilterByStartDateAndEndDate(LocalDateTime dateFrom, LocalDateTime dateTo);

}
