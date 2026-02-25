package com.securin.weather.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.securin.weather.entity.Weather;
import com.securin.weather.repository.WeatherRepository;
import com.securin.weather.service.CsvImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvImportServiceImpl implements CsvImportService {

    private final WeatherRepository weatherRepository;

    @Override
    public void importCsvData(MultipartFile file) {
        log.info("Starting CSV import from uploaded file: {}", file.getOriginalFilename());
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream())).withSkipLines(1)
                .build()) {
            List<String[]> rows = reader.readAll();
            List<Weather> batch = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

            for (String[] row : rows) {
                try {
                    Weather w = new Weather();
                    w.setRecordDate(LocalDateTime.parse(row[0], formatter));
                    w.setWeatherCondition(row[1]);
                    w.setDewptm(parseDouble(row[2]));
                    w.setFog(parseDouble(row[3]));
                    w.setHail(parseDouble(row[4]));
                    w.setHeatinde(parseDouble(row[5]));
                    w.setHumidity(parseDouble(row[6]));
                    w.setPrecipm(parseDouble(row[7]));
                    w.setPressure(parseDouble(row[8]));
                    w.setRain(parseDouble(row[9]));
                    w.setSnow(parseDouble(row[10]));
                    w.setTemperature(parseDouble(row[11]));
                    w.setThunder(parseDouble(row[12]));
                    w.setTornado(parseDouble(row[13]));
                    w.setVism(parseDouble(row[14]));
                    w.setWinddegrees(parseDouble(row[15]));
                    w.setWinddirection(row[16]);
                    w.setWgustm(parseDouble(row[17]));
                    w.setWindchill(parseDouble(row[18]));
                    w.setWspdm(parseDouble(row[19]));
                    batch.add(w);
                    if (batch.size() >= 1000) {
                        weatherRepository.saveAll(batch);
                        batch.clear();
                    }
                } catch (Exception e) {
                    log.warn("Skipping invalid row during CSV read");
                }
            }
            if (!batch.isEmpty()) {
                weatherRepository.saveAll(batch);
            }
            log.info("Finished CSV import");
        } catch (Exception e) {
            log.error("Failed to import CSV data", e);
            throw new RuntimeException("Failed to process CSV file", e);
        }
    }

    private Double parseDouble(String val) {
        try {
            return (val == null || val.isBlank() || val.trim().equalsIgnoreCase("NA")) ? null
                    : Double.parseDouble(val.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
