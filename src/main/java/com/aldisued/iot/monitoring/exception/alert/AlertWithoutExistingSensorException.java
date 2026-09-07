package com.aldisued.iot.monitoring.exception.alert;

public class AlertWithoutExistingSensorException extends RuntimeException {

    public AlertWithoutExistingSensorException(String message) {
        super(message);
    }
}
