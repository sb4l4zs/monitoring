package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.AlertDto;
import com.aldisued.iot.monitoring.entity.Alert;
import com.aldisued.iot.monitoring.exception.alert.NoAlertByIdException;
import com.aldisued.iot.monitoring.exception.sensorReading.NoSensorFoundByIdException;
import com.aldisued.iot.monitoring.repository.AlertRepository;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

  private final AlertRepository alertRepository;
  private final SensorRepository sensorRepository;
  private final KafkaTemplate<String, AlertDto> kafkaTemplate;

  public AlertService(AlertRepository alertRepository, SensorRepository sensorRepository,
      KafkaTemplate<String, AlertDto> kafkaTemplate) {
    this.alertRepository = alertRepository;
    this.sensorRepository = sensorRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  public Alert saveAlert(AlertDto alertDto) {
    Alert alert = new Alert(
            alertDto.message(),
            alertDto.timestamp(),
            sensorRepository.findById(alertDto.sensorId())
                    .orElseThrow(() -> new NoSensorFoundByIdException("Sensor with id " + alertDto.sensorId() + " not found"))
    );
    Alert savedAlert = alertRepository.save(alert);
    kafkaTemplate.send("alerts", alertDto);
    return savedAlert;
  }

  public AlertDto findLastAlertBySensorId(UUID sensorId) {
    return alertRepository.findFirstBySensorIdOrderByTimestampDesc(sensorId)
            .orElseThrow(() -> new NoAlertByIdException("No alert found for sensor with ID: " + sensorId
            ));
  }
}
