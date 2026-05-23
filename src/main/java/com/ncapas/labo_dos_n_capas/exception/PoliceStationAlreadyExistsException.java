package com.ncapas.labo_dos_n_capas.exception;

public class PoliceStationAlreadyExistsException extends RuntimeException {
    public PoliceStationAlreadyExistsException(String stationName) {
        super("Police station with name '" + stationName + "' already exists");
    }
}

