package com.securin.weather.controller;

import com.securin.weather.dto.WeatherDto;
import com.securin.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.securin.weather.service.CsvImportService;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Tag(name = "Weather", description = "Weather Data Retrieval Operations")
public class WeatherController {

    private final WeatherService weatherService;
    private final CsvImportService csvImportService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload CSV Data", description = "Upload the large 20-year weather CSV file to populate the database.")
    public ResponseEntity<String> uploadCsvData(@RequestParam("file") MultipartFile file) {
        try {
            csvImportService.importCsvData(file);
            return ResponseEntity.ok("CSV data successfully imported!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to process CSV file.");
        }
    }

    @GetMapping("/by-date")
    @Operation(summary = "Get weather by date", description = "Retrieve weather details for a specific date across the 20-year span.")
    public ResponseEntity<List<WeatherDto>> getWeatherByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(weatherService.getWeatherByDate(date));
    }

    @GetMapping("/by-month")
    @Operation(summary = "Get weather by month", description = "Retrieve weather details for a specific month and year.")
    public ResponseEntity<List<WeatherDto>> getWeatherByMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        return ResponseEntity.ok(weatherService.getWeatherByMonth(year, month));
    }

}
