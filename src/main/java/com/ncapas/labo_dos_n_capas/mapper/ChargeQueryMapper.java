package com.ncapas.labo_dos_n_capas.mapper;

import com.ncapas.labo_dos_n_capas.domain.dto.response.ChargeByPersonResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.MostWantedPersonResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonBasicResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonWithChargesResponseDto;
import com.ncapas.labo_dos_n_capas.domain.entity.Charge;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class ChargeQueryMapper {

    public PersonBasicResponseDto toPersonBasicResponse(Person person) {
        return PersonBasicResponseDto.builder()
                .id(person.getId())
                .nombre(person.getName())
                .dui(person.getDui())
                .telefono(person.getPhone())
                .build();
    }

    public ChargeByPersonResponseDto toChargeByPersonResponse(Charge charge) {
        return ChargeByPersonResponseDto.builder()
                .fecha(charge.getDate())
                .tipoCargo(charge.getChargeType().name())
                .descripcion(charge.getDescription())
                .nombrePolicia(charge.getPoliceOfficer().getPerson().getName())
                .codigoPolicia(charge.getPoliceOfficer().getCode())
                .nombreAcusador(charge.getAccuser().getName())
                .duiAcusador(charge.getAccuser().getDui())
                .build();
    }

    public PersonWithChargesResponseDto toPersonWithChargesResponse(Object[] row) {
        Person person = (Person) row[0];
        Long cantidadCargos = (Long) row[1];

        return PersonWithChargesResponseDto.builder()
                .id(person.getId())
                .nombre(person.getName())
                .dui(person.getDui())
                .telefono(person.getPhone())
                .cantidadCargos(cantidadCargos)
                .build();
    }

    public MostWantedPersonResponseDto toMostWantedPersonResponse(Object[] row, Integer posicion) {
        Person person = (Person) row[0];
        Long cantidadCargos = (Long) row[1];

        return MostWantedPersonResponseDto.builder()
                .posicion(posicion)
                .id(person.getId())
                .nombre(person.getName())
                .dui(person.getDui())
                .telefono(person.getPhone())
                .cantidadCargos(cantidadCargos)
                .build();
    }
}