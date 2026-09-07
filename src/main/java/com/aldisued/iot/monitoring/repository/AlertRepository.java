package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.dto.AlertDto;
import com.aldisued.iot.monitoring.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, String> {
    Optional<AlertDto> findFirstBySensorIdOrderByTimestampDesc(UUID sensorId);
}
