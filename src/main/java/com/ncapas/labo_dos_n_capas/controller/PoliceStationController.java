package com.ncapas.labo_dos_n_capas.controller;

import com.ncapas.labo_dos_n_capas.domain.dto.GenericResponse;
import com.ncapas.labo_dos_n_capas.domain.dto.PoliceStationDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PoliceStationResponseDTO;
import com.ncapas.labo_dos_n_capas.service.impl.PoliceStationServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/police-stations")
@AllArgsConstructor
public class PoliceStationController {
    private final PoliceStationServiceImpl policeStationService;

    @PostMapping
    public ResponseEntity<GenericResponse> createPoliceStation(@Valid @RequestBody PoliceStationDTO policeStationDTO) {
        PoliceStationResponseDTO createdStation = policeStationService.createPoliceStation(policeStationDTO);
        return GenericResponse.builder()
                .message("Police station created successfully")
                .data(createdStation)
                .status(HttpStatus.CREATED)
                .build()
                .buildResponse();
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPoliceStations() {
        List<PoliceStationResponseDTO> stations = policeStationService.getAllPoliceStations();
        return GenericResponse.builder()
                .message("All police stations retrieved successfully")
                .data(stations)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getPoliceStation(@PathVariable UUID id) {
        PoliceStationResponseDTO station = policeStationService.getPoliceStationById(id);
        return GenericResponse.builder()
                .message("Police station retrieved successfully")
                .data(station)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updatePoliceStation(
            @PathVariable UUID id,
            @Valid @RequestBody PoliceStationDTO policeStationDTO
    ) {
        PoliceStationResponseDTO updatedStation = policeStationService.updatePoliceStation(id, policeStationDTO);
        return GenericResponse.builder()
                .message("Police station updated successfully")
                .data(updatedStation)
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deletePoliceStation(
            @PathVariable UUID id
    ) {
        policeStationService.deletePoliceStation(id);
        return GenericResponse.builder()
                .message("Police station deleted successfully")
                .status(HttpStatus.OK)
                .build()
                .buildResponse();
    }
}

