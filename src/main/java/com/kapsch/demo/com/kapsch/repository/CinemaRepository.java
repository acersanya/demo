package com.kapsch.demo.com.kapsch.repository;

import com.kapsch.demo.com.kapsch.model.Cinema;
import com.kapsch.demo.com.kapsch.model.Movie;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query("select c.movies from Cinema c where c.id = :id ")
    public Set<Movie> getById(@Param("id") Long id);

    @Query(value = " SELECT h.name "
        + "FROM "
        + "  (SELECT c.name AS name, "
        + "          COUNT (s.session_id) AS sId "
        + "   FROM public.cinema c "
        + "   INNER JOIN public.movie_cinema mc ON c.cinema_id=mc.cinema_id "
        + "   INNER JOIN public.movie m ON mc.movie_id=m.movie_id "
        + "   INNER JOIN public.session s ON m.movie_id=s.movie "
        + "   WHERE s.start > :fromDate "
        + "     AND s.end < :toDate "
        + "   GROUP BY c.cinema_id) h "
        + " WHERE h.sId > 5 ", nativeQuery = true)
    public Set<String> getCinemaNamesWithMoreThanFiveSessions(@Param("fromDate") LocalDateTime from, @Param("toDate") LocalDateTime to);
}


