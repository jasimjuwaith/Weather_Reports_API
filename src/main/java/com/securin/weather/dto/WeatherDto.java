package com.securin.weather.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDto {
    private LocalDateTime recordDate;
    private String weatherCondition;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
