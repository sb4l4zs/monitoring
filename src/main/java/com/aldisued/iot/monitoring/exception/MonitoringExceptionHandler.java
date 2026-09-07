package com.aldisued.iot.monitoring.exception;

import com.aldisued.iot.monitoring.exception.sensor.SensorNameAlreadyExistsException;
import com.aldisued.iot.monitoring.exception.sensorReading.NoSensorFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MonitoringExceptionHandler {

    @ExceptionHandler(NoSensorFoundByIdException.class)
    public ResponseEntity<String> handleNoSensorFoundById(NoSensorFoundByIdException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(SensorNameAlreadyExistsException.class)
    public ResponseEntity<String> handleSensorNameExists(SensorNameAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
