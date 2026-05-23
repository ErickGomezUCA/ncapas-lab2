package com.ncapas.labo_dos_n_capas.repository;

import com.ncapas.labo_dos_n_capas.domain.entity.Charge;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, UUID> {
    Charge findByAccuserAndAccused(Person accuser, Person accused);
}

