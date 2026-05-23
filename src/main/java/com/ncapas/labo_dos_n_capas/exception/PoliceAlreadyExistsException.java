package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class PoliceAlreadyExistsException extends RuntimeException {
    public PoliceAlreadyExistsException(String code) {
        super("Police officer with code '" + code + "' already exists");
    }
}

