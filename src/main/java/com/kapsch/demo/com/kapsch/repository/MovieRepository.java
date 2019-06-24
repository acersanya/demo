package com.kapsch.demo.com.kapsch.repository;

import com.kapsch.demo.com.kapsch.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
