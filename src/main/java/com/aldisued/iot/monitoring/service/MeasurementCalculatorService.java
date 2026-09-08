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
        List<Double> movingAverages = new ArrayList<>();
        if (windowSize < 1 || data.isEmpty())
            throw new IllegalArgumentException("windowSize must be greater than 0 and data can't be empty");
        if (windowSize > data.size())
            return data;

        double sum = 0.0;
        for (int i = 0; i < windowSize; i++) {
            sum += data.get(i);
        }
        movingAverages.add(sum / windowSize);

        for (int i = windowSize; i < data.size(); i++) {
            sum += data.get(i) - data.get(i - windowSize);
            movingAverages.add(sum / windowSize);
        }
        return movingAverages;
    }


}
