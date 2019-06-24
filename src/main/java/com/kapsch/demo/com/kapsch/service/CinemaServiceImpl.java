package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.dto.AggregationDTO;
import com.kapsch.demo.com.kapsch.model.Cinema;
import com.kapsch.demo.com.kapsch.repository.CinemaRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CinemaRepository cinemaRepository;

    @Override
    public Cinema getCinema(Long id) {
        return cinemaRepository.getOne(id);
    }

    @Override
    public Cinema getCinemaJoinFetch(Long id) {
        return cinemaRepository.getByIdFetchMovies(id);
    }

    @Override
    public Cinema getCinemaEntityGraph(Long id) {
        EntityGraph graph = entityManager.getEntityGraph(Cinema.MOVIES);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(Cinema.class, id, properties);
    }

    @Override
    public Set<String> getCinemaBySessionAmountFilterByStartDateAndEndDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return cinemaRepository.getCinemaNamesWithMoreThanFiveSessions(dateFrom, dateTo);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public AggregationDTO getTotalPricePerMovieFilterByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Long movieId, Long cinemaId) {
        return cinemaRepository.getCinemaTotalMoney(dateFrom, dateTo, movieId, cinemaId);
    }
}
