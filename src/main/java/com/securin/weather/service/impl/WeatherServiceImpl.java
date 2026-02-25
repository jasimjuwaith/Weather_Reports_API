package com.securin.weather.service.impl;

import com.securin.weather.dto.WeatherDto;
import com.securin.weather.entity.Weather;
import com.securin.weather.repository.WeatherRepository;
import com.securin.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public List<WeatherDto> getWeatherByDate(LocalDate date) {
        return weatherRepository.findByRecordDate(date)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WeatherDto> getWeatherByMonth(int year, int month) {
        return weatherRepository.findByYearAndMonth(year, month)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private WeatherDto mapToDto(Weather weather) {
        return WeatherDto.builder()
                .recordDate(weather.getRecordDate())
                .weatherCondition(weather.getWeatherCondition())
                .temperature(weather.getTemperature())
                .humidity(weather.getHumidity())
                .pressure(weather.getPressure())
                .build();
    }
}
