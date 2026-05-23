package com.ncapas.labo_dos_n_capas.service;

import com.ncapas.labo_dos_n_capas.domain.dto.response.MostWantedPersonResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonChargesResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonWithChargesResponseDto;

import java.util.List;
import java.util.UUID;

public interface ChargeQueryService {

    PersonChargesResponseDto getChargesByPerson(UUID personId);

    List<PersonWithChargesResponseDto> getPersonsWithCharges();

    List<MostWantedPersonResponseDto> getTop3MostWantedPersons();
}