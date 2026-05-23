package com.ncapas.labo_dos_n_capas.controller;

import com.ncapas.labo_dos_n_capas.domain.dto.GenericResponse;
import com.ncapas.labo_dos_n_capas.domain.dto.PersonDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonResponseDTO;
import com.ncapas.labo_dos_n_capas.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {
    private final PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<GenericResponse> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        PersonResponseDTO createdPerson = personService.createPerson(personDTO);
        return GenericResponse.builder()
                .message("Person created successfully")
                .data(createdPerson)
                .status(HttpStatus.CREATED)
                .build()
                .buildResponse();
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPeople() {
        List<PersonResponseDTO> people = personService.getAllPeople();
        return GenericResponse.builder()
                .message("All people retrieved successfully")
                .data(people)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping("/dui/{dui}")
    public ResponseEntity<GenericResponse> getPersonByDui(@PathVariable String dui) {
        PersonResponseDTO person = personService.getPersonByDui(dui);
        return GenericResponse.builder()
                .message("Person retrieved successfully by DUI")
                .data(person)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getPerson(@PathVariable UUID id) {
        PersonResponseDTO person = personService.getPersonById(id);
        return GenericResponse.builder()
                .message("Person retrieved successfully")
                .data(person)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updatePerson(
            @PathVariable UUID id,
            @Valid @RequestBody PersonDTO personDTO
    ) {
        PersonResponseDTO updatedPerson = personService.updatePerson(id, personDTO);
        return GenericResponse.builder()
                .message("Person updated successfully")
                .data(updatedPerson)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deletePerson(
            @PathVariable UUID id
    ) {
        personService.deletePerson(id);
        return GenericResponse.builder()
                .message("Person deleted successfully")
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }
}
