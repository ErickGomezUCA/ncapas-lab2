package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class ChargeNotFoundException extends RuntimeException {
    public ChargeNotFoundException(UUID id) {
        super("Charge with ID '" + id + "' not found");
    }
}

