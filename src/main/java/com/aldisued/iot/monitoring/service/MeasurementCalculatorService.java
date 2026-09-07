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
        else if (windowSize > data.size())
            return data;
        else {
            for (int i = 0; i <= data.size() - windowSize; i++) {
                movingAverages.add(calculateAverage(data, i, windowSize));
            }
        }
        return movingAverages;
    }

    private Double calculateAverage(List<Double> data, int startIndex, int windowSize) {
        Double sum = 0.0;
        for (int i = startIndex; i < startIndex + windowSize; i++) {
            sum += data.get(i);
        }
        return sum / windowSize;
    }

}
