package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.model.Session;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public Session persist(Date startTime, Date endTime) {
        return Session
            .builder()
//            .startTime(startTime)
//            .endTime(endTime)
            .build();
    }
}

//    select movie2_.movie_id as movie_id1_1_, movie2_.duration as duration2_1_, movie2_.name as name3_1_
//    from public.cinema cinema0_
//    inner join public.movie_cinema movies1_ on cinema0_.cinema_id=movies1_.cinema_id
//    inner join public.movie movie2_ on movies1_.movie_id=movie2_.movie_id where cinema0_.cinema_id=?
