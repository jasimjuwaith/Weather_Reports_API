package com.securin.weather.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvImportService {
    void importCsvData(MultipartFile file);
}
