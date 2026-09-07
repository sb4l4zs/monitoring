package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.dto.MeasurementValuesDto;
import com.aldisued.iot.monitoring.dto.SensorReadingDto;
import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {
    @Query("""
            select sr.value, sr.timestamp
            from SensorReading sr
            where sr.sensor.type = :sensorType
              and sr.timestamp between :from and :to
            order by sr.timestamp
            """)
    List<MeasurementValuesDto> findValuesAndTimestampsBySensor_TypeAndTimestampBetween(
            SensorType sensorType,
            LocalDateTime from,
            LocalDateTime to
    );

    @Query("""
            select sr.value
            from SensorReading sr
            where sr.sensor.type = :sensorType
              and sr.timestamp between :from and :to
            """)
    List<Double> findValuesBySensor_TypeAndTimestampBetween(
            SensorType sensorType,
            LocalDateTime from,
            LocalDateTime to
    );
}
