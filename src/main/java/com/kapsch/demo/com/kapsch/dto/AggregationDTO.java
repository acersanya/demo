package com.kapsch.demo.com.kapsch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AggregationDTO {

    private String movieName;
    private Double totalPrice;
    private Integer totalVisitors;
}
