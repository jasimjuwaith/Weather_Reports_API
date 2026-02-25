package com.securin.weather.config;

import com.securin.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final WeatherRepository weatherRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Checking database state...");

        if (weatherRepository.count() == 0) {
            log.info(
                    "Database is CURRENTLY EMPTY. Please navigate to Swagger UI to upload the CSV dataset using the /upload endpoint.");
        } else {
            log.info("Database contains data ({} records). Ready to serve API requests.", weatherRepository.count());
        }
    }
}
