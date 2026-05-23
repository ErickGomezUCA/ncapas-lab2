package com.ncapas.labo_dos_n_capas.service;

import com.ncapas.labo_dos_n_capas.domain.dto.PersonDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    PersonResponseDTO createPerson(PersonDTO personDTO);
    PersonResponseDTO getPersonById(UUID id);
    PersonResponseDTO getPersonByDui(String dui);
    List<PersonResponseDTO> getAllPeople();
    PersonResponseDTO updatePerson(UUID id, PersonDTO personDTO);
    void deletePerson(UUID id);
}
