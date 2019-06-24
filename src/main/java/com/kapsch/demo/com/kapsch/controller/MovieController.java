package com.kapsch.demo.com.kapsch.controller;


import com.kapsch.demo.com.kapsch.dto.MovieDTO;
import com.kapsch.demo.com.kapsch.model.Movie;
import com.kapsch.demo.com.kapsch.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/v0/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getCinemasBySessionFilterDate(
        @PathVariable(value = "movieId") Long movieId) {
        return ResponseEntity.of(movieRepository.findById(movieId));
    }

    @PostMapping(consumes = "application/json")
    public void createMovie(@RequestBody MovieDTO movieDTO) {
        log.debug("movieDTO = {}", movieDTO);
    }

}
