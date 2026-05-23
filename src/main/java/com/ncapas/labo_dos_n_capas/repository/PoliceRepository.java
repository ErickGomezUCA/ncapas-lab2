package com.ncapas.labo_dos_n_capas.repository;

import com.ncapas.labo_dos_n_capas.domain.entity.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PoliceRepository extends JpaRepository<PoliceOfficer, UUID> {
    PoliceOfficer findByCode(String code);
}

