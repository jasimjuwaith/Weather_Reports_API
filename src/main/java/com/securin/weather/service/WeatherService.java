package com.securin.weather.service;

import com.securin.weather.dto.WeatherDto;
import java.time.LocalDate;
import java.util.List;

public interface WeatherService {
    List<WeatherDto> getWeatherByDate(LocalDate date);

    List<WeatherDto> getWeatherByMonth(int year, int month);
}
