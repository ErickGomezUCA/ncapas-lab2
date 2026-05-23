package com.ncapas.labo_dos_n_capas.service.impl;

import com.ncapas.labo_dos_n_capas.domain.dto.PersonDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonResponseDTO;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import com.ncapas.labo_dos_n_capas.domain.entity.location.Address;
import com.ncapas.labo_dos_n_capas.exception.PersonAlreadyExistsException;
import com.ncapas.labo_dos_n_capas.exception.PersonNotFoundException;
import com.ncapas.labo_dos_n_capas.repository.PersonRepository;
import com.ncapas.labo_dos_n_capas.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public PersonResponseDTO createPerson(PersonDTO personDTO) {
        // Check if person already exists by DUI
        Person existingPerson = personRepository.findByDui(personDTO.getDui());
        if (existingPerson != null) {
            throw new PersonAlreadyExistsException(personDTO.getDui());
        }

        Person person = Person.builder()
                .name(personDTO.getName())
                .dui(personDTO.getDui())
                .phone(personDTO.getPhone())
                .address(Address.builder().id(UUID.fromString(personDTO.getAddressId())).build())
                .build();

        Person savedPerson = personRepository.save(person);
        return convertToResponseDTO(savedPerson);
    }

    @Override
    public PersonResponseDTO getPersonById(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return convertToResponseDTO(person);
    }

    @Override
    public PersonResponseDTO getPersonByDui(String dui) {
        Person person = personRepository.findByDui(dui);
        if (person == null) {
            throw new PersonNotFoundException(dui);
        }
        return convertToResponseDTO(person);
    }

    @Override
    public List<PersonResponseDTO> getAllPeople() {
        return personRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonResponseDTO updatePerson(UUID id, PersonDTO personDTO) {
        // Check if person exists
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        // Check if DUI is being changed to a value that already exists
        if (!existingPerson.getDui().equals(personDTO.getDui())) {
            Person personWithDui = personRepository.findByDui(personDTO.getDui());
            if (personWithDui != null) {
                throw new PersonAlreadyExistsException(personDTO.getDui());
            }
        }

        existingPerson.setName(personDTO.getName());
        existingPerson.setDui(personDTO.getDui());
        existingPerson.setPhone(personDTO.getPhone());
        if (personDTO.getAddressId() != null) {
            existingPerson.setAddress(Address.builder().id(UUID.fromString(personDTO.getAddressId())).build());
        }

        Person updatedPerson = personRepository.save(existingPerson);
        return convertToResponseDTO(updatedPerson);
    }

    @Override
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

    private PersonResponseDTO convertToResponseDTO(Person person) {
        return PersonResponseDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .dui(person.getDui())
                .phone(person.getPhone())
                .addressId(person.getAddress().getId())
                .build();
    }
}
