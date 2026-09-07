package com.aldisued.iot.monitoring.dto;

import java.time.LocalDateTime;

public record MeasurementValuesDto(
        Double value,
        LocalDateTime timestamp
) {}
