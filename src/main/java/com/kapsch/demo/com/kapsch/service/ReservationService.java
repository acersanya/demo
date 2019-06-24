package com.kapsch.demo.com.kapsch.service;

import java.time.LocalDateTime;
import java.util.Set;

public interface ReservationService {

    Cinema getCinema(Long id);

    Cinema getCinemaJoinFetch(Long id);

    Set<String> getAllMoviesByCinemaFilterByStartDateAndEndDate(LocalDateTime dateFrom, LocalDateTime dateTo);

}
