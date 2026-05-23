package com.ncapas.labo_dos_n_capas.exception;

import java.util.UUID;

public class PersonNotFoundByDUIException extends RuntimeException {
    public PersonNotFoundByDUIException(String dui) {
        super("Person with DUI '" + dui + "' not found");
    }
}
