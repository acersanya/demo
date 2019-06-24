package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.dto.AggregationDTO;
import com.kapsch.demo.com.kapsch.model.Cinema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CinemaService {

    Cinema getCinema(Long id);

    Cinema getCinemaJoinFetch(Long id);

    Cinema getCinemaEntityGraph(Long id);

    Set<String> getCinemaBySessionAmountFilterByStartDateAndEndDate(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Cinema> getAllCinemas();

    AggregationDTO getTotalPricePerMovieFilterByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Long movieId, Long cinemaId);
}
