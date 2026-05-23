package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class PoliceStationNotFoundException extends RuntimeException {
    public PoliceStationNotFoundException(UUID id) {
        super("Police station with ID '" + id + "' not found");
    }
}

