package com.aldisued.iot.monitoring.dto;

import com.aldisued.iot.monitoring.entity.SensorType;
import jakarta.validation.constraints.NotEmpty;

public record SensorDto(
    @NotEmpty(message = "Sensor name must be provided")
    String name,
    SensorType type
) {
}
