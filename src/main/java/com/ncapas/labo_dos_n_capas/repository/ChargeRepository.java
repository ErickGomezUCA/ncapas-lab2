package com.ncapas.labo_dos_n_capas.repository;

import com.ncapas.labo_dos_n_capas.domain.entity.Charge;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChargeRepository extends JpaRepository<Charge, UUID> {

    List<Charge> findByAccused_Id(UUID accusedId);

    @Query("SELECT c.accused, COUNT(c.id) " +
            "FROM Charge c " +
            "GROUP BY c.accused")
    List<Object[]> findPersonsWithCharges();

    @Query("SELECT c.accused, COUNT(c.id) " +
            "FROM Charge c " +
            "GROUP BY c.accused " +
            "ORDER BY COUNT(c.id) DESC")
    List<Object[]> findTopMostWantedPersons(Pageable pageable);
}