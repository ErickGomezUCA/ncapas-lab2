package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(UUID id) {
        super("Person with ID '" + id + "' not found");
    }
}

