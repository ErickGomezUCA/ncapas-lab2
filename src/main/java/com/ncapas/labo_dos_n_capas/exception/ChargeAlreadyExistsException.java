package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class ChargeAlreadyExistsException extends RuntimeException {
    public ChargeAlreadyExistsException(UUID accuserId, UUID accusedId) {
        super("Charge already exists for accuser '" + accuserId + "' and accused '" + accusedId + "'");
    }
}

