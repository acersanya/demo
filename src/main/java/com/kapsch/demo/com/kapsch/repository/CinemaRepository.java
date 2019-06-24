package com.kapsch.demo.com.kapsch.repository;

import com.kapsch.demo.com.kapsch.dto.AggregationDTO;
import com.kapsch.demo.com.kapsch.model.Cinema;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query(value = "select c from Cinema c "
        + " join fetch c.movies m "
        + " where c.id = :id ")
    Cinema getByIdFetchMovies(@Param("id") Long id);

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
    Set<String> getCinemaNamesWithMoreThanFiveSessions(@Param("fromDate") LocalDateTime from, @Param("toDate") LocalDateTime to);

    @Query(value = "SELECT m.name, "
        + "\t\tm.movie_id, "
        + "       counts.ticket_price, "
        + "       counts.visit_num "
        + "FROM public.cinema c "
        + "INNER JOIN public.movie_cinema mc ON c.cinema_id = mc.cinema_id "
        + "INNER JOIN public.movie m ON mc.movie_id = m.movie_id "
        + "INNER JOIN "
        + "  (SELECT s.movie, "
        + "          sum(t.price) AS ticket_price, "
        + "          count(t.ticket_id) visit_num "
        + "   FROM public.session AS s "
        + "   INNER JOIN public.ticket AS t ON t.session_id = s.session_id "
        + "   WHERE s.start > :fromDate AND s.end < :toDate "
        + "   GROUP BY s.movie) AS counts ON counts.movie = m.movie_id "
        + "WHERE c.cinema_id = :cinemaId and m.movie_id = :movieId", nativeQuery = true)
    AggregationDTO getCinemaTotalMoney(
        @Param("fromDate") LocalDateTime from,
        @Param("toDate") LocalDateTime to,
        @Param("cinemaId") Long cinemaId,
        @Param("movieId") Long movieId
    );
}


