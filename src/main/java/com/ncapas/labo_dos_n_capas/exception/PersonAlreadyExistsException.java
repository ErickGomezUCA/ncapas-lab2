package com.ncapas.labo_dos_n_capas.exception;

public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException(String dui) {
        super("Person with DUI '" + dui + "' already exists");
    }
}

