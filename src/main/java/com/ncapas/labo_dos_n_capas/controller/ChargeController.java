package com.ncapas.labo_dos_n_capas.controller;

import com.ncapas.labo_dos_n_capas.domain.dto.ChargeDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.ChargeResponseDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.GenericResponse;
import com.ncapas.labo_dos_n_capas.service.impl.ChargeServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/charges")
@AllArgsConstructor
public class ChargeController {
    private final ChargeServiceImpl chargeService;

    @PostMapping
    public ResponseEntity<GenericResponse> createCharge(@Valid @RequestBody ChargeDTO chargeDTO) {
        ChargeResponseDTO createdCharge = chargeService.createCharge(chargeDTO);
        return GenericResponse.builder()
                .message("Charge created successfully")
                .data(createdCharge)
                .status(HttpStatus.CREATED)
                .build()
                .buildResponse();
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllCharges() {
        List<ChargeResponseDTO> charges = chargeService.getAllCharges();
        return GenericResponse.builder()
                .message("All charges retrieved successfully")
                .data(charges)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getCharge(@PathVariable UUID id) {
        ChargeResponseDTO charge = chargeService.getChargeById(id);
        return GenericResponse.builder()
                .message("Charge retrieved successfully")
                .data(charge)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updateCharge(
            @PathVariable UUID id,
            @Valid @RequestBody ChargeDTO chargeDTO
    ) {
        ChargeResponseDTO updatedCharge = chargeService.updateCharge(id, chargeDTO);
        return GenericResponse.builder()
                .message("Charge updated successfully")
                .data(updatedCharge)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteCharge(
            @PathVariable UUID id
    ) {
        chargeService.deleteCharge(id);
        return GenericResponse.builder()
                .message("Charge deleted successfully")
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }
}

