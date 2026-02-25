package com.securin.weather.repository;

import com.securin.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("SELECT w FROM Weather w WHERE DATE(w.recordDate) = :date")
    List<Weather> findByRecordDate(@Param("date") LocalDate date);

    @Query("SELECT w FROM Weather w WHERE YEAR(w.recordDate) = :year AND MONTH(w.recordDate) = :month")
    List<Weather> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT w FROM Weather w WHERE YEAR(w.recordDate) = :year")
    List<Weather> findByYear(@Param("year") int year);
}
