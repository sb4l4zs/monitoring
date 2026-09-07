package com.aldisued.iot.monitoring.exception.sensorReading;

public class NoSensorFoundByIdException extends RuntimeException {

    public NoSensorFoundByIdException(String message) {
        super(message);
    }
}