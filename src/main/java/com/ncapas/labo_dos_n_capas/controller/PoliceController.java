package com.ncapas.labo_dos_n_capas.controller;

import com.ncapas.labo_dos_n_capas.domain.dto.GenericResponse;
import com.ncapas.labo_dos_n_capas.domain.dto.PoliceDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PoliceResponseDTO;
import com.ncapas.labo_dos_n_capas.service.impl.PoliceServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/police")
@AllArgsConstructor
public class PoliceController {
    private final PoliceServiceImpl policeService;

    @PostMapping
    public ResponseEntity<GenericResponse> createPolice(@Valid @RequestBody PoliceDTO policeDTO) {
        PoliceResponseDTO createdPolice = policeService.createPolice(policeDTO);
        return GenericResponse.builder()
                .message("Police officer created successfully")
                .data(createdPolice)
                .status(HttpStatus.CREATED)
                .build()
                .buildResponse();
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPolice() {
        List<PoliceResponseDTO> police = policeService.getAllPolice();
        return GenericResponse.builder()
                .message("All police officers retrieved successfully")
                .data(police)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<GenericResponse> getPoliceByCode(@PathVariable String code) {
        PoliceResponseDTO police = policeService.getPoliceByCode(code);
        return GenericResponse.builder()
                .message("Police officer retrieved successfully by code")
                .data(police)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getPolice(@PathVariable UUID id) {
        PoliceResponseDTO police = policeService.getPoliceById(id);
        return GenericResponse.builder()
                .message("Police officer retrieved successfully")
                .data(police)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updatePolice(
            @PathVariable UUID id,
            @Valid @RequestBody PoliceDTO policeDTO
    ) {
        PoliceResponseDTO updatedPolice = policeService.updatePolice(id, policeDTO);
        return GenericResponse.builder()
                .message("Police officer updated successfully")
                .data(updatedPolice)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deletePolice(
            @PathVariable UUID id
    ) {
        policeService.deletePolice(id);
        return GenericResponse.builder()
                .message("Police officer deleted successfully")
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }
}

