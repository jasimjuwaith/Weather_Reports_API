package com.securin.weather.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthlyStatsDto {
    private Double high;
    private Double median;
    private Double minimum;
}
