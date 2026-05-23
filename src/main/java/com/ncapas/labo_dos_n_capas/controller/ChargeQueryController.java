package com.ncapas.labo_dos_n_capas.controller;

import com.ncapas.labo_dos_n_capas.domain.dto.response.MostWantedPersonResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonChargesResponseDto;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PersonWithChargesResponseDto;
import com.ncapas.labo_dos_n_capas.service.ChargeQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class ChargeQueryController {

    private final ChargeQueryService chargeQueryService;

    @GetMapping("/{id}/charges")
    public ResponseEntity<PersonChargesResponseDto> getChargesByPerson(@PathVariable UUID id) {
        return ResponseEntity.ok(chargeQueryService.getChargesByPerson(id));
    }

    @GetMapping("/with-charges")
    public ResponseEntity<List<PersonWithChargesResponseDto>> getPersonsWithCharges() {
        return ResponseEntity.ok(chargeQueryService.getPersonsWithCharges());
    }

    @GetMapping("/top-3-most-wanted")
    public ResponseEntity<List<MostWantedPersonResponseDto>> getTop3MostWantedPersons() {
        return ResponseEntity.ok(chargeQueryService.getTop3MostWantedPersons());
    }
}