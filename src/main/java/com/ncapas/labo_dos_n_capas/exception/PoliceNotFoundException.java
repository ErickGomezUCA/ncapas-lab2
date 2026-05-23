package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class PoliceNotFoundException extends RuntimeException {
    public PoliceNotFoundException(UUID id) {
        super("Police officer with ID '" + id + "' not found");
    }

    public PoliceNotFoundException(String code) {
        super("Police officer with code '" + code + "' not found");
    }
}

