package com.aldisued.iot.monitoring.exception.sensor;

public class SensorNameAlreadyExistsException extends RuntimeException {

    public SensorNameAlreadyExistsException(String message) {
        super(message);
    }
}
