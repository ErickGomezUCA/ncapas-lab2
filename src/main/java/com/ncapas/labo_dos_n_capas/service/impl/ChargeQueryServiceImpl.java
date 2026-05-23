package com.ncapas.labo_dos_n_capas.service.impl;

import com.ncapas.labo_dos_n_capas.domain.dto.response.ChargeByPersonResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.MostWantedPersonResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonChargesResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonWithChargesResponseDto;
import com.ncapas.labo_dos_n_capas.domain.entity.Charge;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import com.ncapas.labo_dos_n_capas.exception.PersonNotFoundException;
import com.ncapas.labo_dos_n_capas.mapper.ChargeQueryMapper;
import com.ncapas.labo_dos_n_capas.repository.ChargeRepository;
import com.ncapas.labo_dos_n_capas.repository.PersonRepository;
import com.ncapas.labo_dos_n_capas.service.ChargeQueryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ChargeQueryServiceImpl implements ChargeQueryService {

    private final PersonRepository personRepository;

    private final ChargeRepository chargeRepository;

    private final ChargeQueryMapper chargeQueryMapper;

    @Override
    @Transactional(readOnly = true)
    public PersonChargesResponseDto getChargesByPerson(UUID personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("La persona no fue encontrada"));

        List<Charge> charges = chargeRepository.findByAccused_Id(personId);

        List<ChargeByPersonResponseDto> chargeResponses = charges.stream()
                .map(chargeQueryMapper::toChargeByPersonResponse)
                .toList();

        return PersonChargesResponseDto.builder()
                .persona(chargeQueryMapper.toPersonBasicResponse(person))
                .cargos(chargeResponses)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonWithChargesResponseDto> getPersonsWithCharges() {
        return chargeRepository.findPersonsWithCharges()
                .stream()
                .map(chargeQueryMapper::toPersonWithChargesResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MostWantedPersonResponseDto> getTop3MostWantedPersons() {
        List<Object[]> results = chargeRepository.findTopMostWantedPersons(PageRequest.of(0, 3));

        return IntStream.range(0, results.size())
                .mapToObj(index -> chargeQueryMapper.toMostWantedPersonResponse(
                        results.get(index),
                        index + 1
                ))
                .toList();
    }
}