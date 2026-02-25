package com.securin.weather.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "weather_data")
@Data
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime recordDate;
    private String weatherCondition;
    private Double dewptm;
    private Double fog;
    private Double hail;
    private Double heatinde;
    private Double humidity;
    private Double precipm;
    private Double pressure;
    private Double rain;
    private Double snow;
    private Double temperature;
    private Double thunder;
    private Double tornado;
    private Double vism;
    private Double winddegrees;
    private String winddirection;
    private Double wgustm;
    private Double windchill;
    private Double wspdm;
}

