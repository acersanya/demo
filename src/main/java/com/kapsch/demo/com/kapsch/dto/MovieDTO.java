package com.kapsch.demo.com.kapsch.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MovieDTO {

    private String name;
    private LocalDateTime duration;
}
