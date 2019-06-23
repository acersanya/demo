package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.model.Movie;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    public Movie persist(String name, Timestamp timestamp) {
        return null;
//        return Movie
//            .builder()
//            .name(name)
//            .duration(timestamp)
//            .build();
    }
}
