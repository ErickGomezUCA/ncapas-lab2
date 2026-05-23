package com.ncapas.labo_dos_n_capas.repository;

import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByDui(String dui);
    Boolean existsByDui(String dui);
}