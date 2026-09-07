package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.MeasurementValuesDto;
import com.aldisued.iot.monitoring.entity.SensorType;
import com.aldisued.iot.monitoring.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class MeasurementService {

    private final SensorReadingRepository sensorReadingRepository;

    public MeasurementService(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public List<Double> getMeasurementValuesBySensorType(SensorType sensorType, LocalDateTime from,
                                                         LocalDateTime to) {
        return sensorReadingRepository.findValuesAndTimestampsBySensor_TypeAndTimestampBetween(sensorType, from, to)
                .stream()
                .map(MeasurementValuesDto::value)
                .toList();
    }

    public Optional<Double> getAverageTemperature(LocalDateTime from, LocalDateTime to) {
        OptionalDouble average = sensorReadingRepository.findValuesBySensor_TypeAndTimestampBetween(SensorType.TEMPERATURE, from, to)
                .stream()
                .mapToDouble(Double::doubleValue)
                .average();

        return average.isPresent() ? Optional.of(average.getAsDouble()) : Optional.empty();
    }

}
