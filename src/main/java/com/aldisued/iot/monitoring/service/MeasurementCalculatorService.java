package com.aldisued.iot.monitoring.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MeasurementCalculatorService {

    public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
        if (deviation < 0.0 || deviation > 1.0) {
            throw new IllegalArgumentException("value of deviation must be between 0.0 and 1.0");
        }

        double avg = values.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return values.stream()
                .filter(value -> Math.abs(value - avg) <= deviation * avg)
                .toList();
    }

    public List<Double> getMovingAverage(List<Double> data, int windowSize) {
        // TODO: Task 10
        return List.of();
    }

}
