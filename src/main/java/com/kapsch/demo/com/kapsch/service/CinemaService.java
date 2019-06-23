package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.model.Cinema;
import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.repository.CinemaRepository;
import com.kapsch.demo.com.kapsch.repository.MovieRepository;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Cinema persist(String name, String address, Set<Movie> movies) {
//        Cinema cinema = Cinema
//            .builder()
//            .name(name)
//            .address(address)
//            .movies(movies)
//            .build();
//        Movie movie = Movie.builder().name("HY").duration(new Timestamp(new Date().getTime())).build();

//        Movie temp = movieRepository.save(movie);
    return null;
//        return cinemaRepository.save(Cinema.builder().name("alex").address("asda").movies(new HashSet<>(Collections.singleton(temp))).build());
    }
}
