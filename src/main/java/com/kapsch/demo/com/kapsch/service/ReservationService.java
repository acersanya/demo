package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.model.Cinema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ReservationService {

    Cinema getCinema(Long id);

    Cinema getCinemaJoinFetch(Long id);

    Cinema getCinemaEntityGraph(Long id);

    Set<String> getCinemaBySessionAmountFilterByStartDateAndEndDate(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Cinema> getAllCinemas();
}
