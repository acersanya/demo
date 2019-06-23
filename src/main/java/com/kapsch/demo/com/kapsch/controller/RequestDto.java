package com.kapsch.demo.com.kapsch.controller;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDto {

    private String cinemaName;

    private String cinemaAddress;

    private String movieName;

    private Timestamp movieDuration;

    private Date sessionStartTime;

    private Date sessionEndTime;

    private Double ticketPrice;

    private String ticketClient;

}
