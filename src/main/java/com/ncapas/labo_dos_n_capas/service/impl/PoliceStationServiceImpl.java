package com.ncapas.labo_dos_n_capas.service.impl;

import com.ncapas.labo_dos_n_capas.domain.dto.PoliceStationDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PoliceStationResponseDTO;
import com.ncapas.labo_dos_n_capas.domain.entity.PoliceStation;
import com.ncapas.labo_dos_n_capas.domain.entity.PoliceOfficer;
import com.ncapas.labo_dos_n_capas.domain.entity.location.Address;
import com.ncapas.labo_dos_n_capas.exception.PoliceStationAlreadyExistsException;
import com.ncapas.labo_dos_n_capas.exception.PoliceStationNotFoundException;
import com.ncapas.labo_dos_n_capas.repository.PoliceStationRepository;
import com.ncapas.labo_dos_n_capas.service.PoliceStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoliceStationServiceImpl implements PoliceStationService {
    private final PoliceStationRepository policeStationRepository;

    @Override
    public PoliceStationResponseDTO createPoliceStation(PoliceStationDTO policeStationDTO) {
        // Check if police station already exists by name
        PoliceStation existingStation = policeStationRepository.findByStationName(policeStationDTO.getStationName());
        if (existingStation != null) {
            throw new PoliceStationAlreadyExistsException(policeStationDTO.getStationName());
        }

        PoliceStation.PoliceStationBuilder builder = PoliceStation.builder()
                .stationName(policeStationDTO.getStationName())
                .address(Address.builder().id(UUID.fromString(policeStationDTO.getAddressId())).build());

        // Set director if directorId is provided
        if (policeStationDTO.getDirectorId() != null && !policeStationDTO.getDirectorId().isBlank()) {
            builder.director(PoliceOfficer.builder().id(UUID.fromString(policeStationDTO.getDirectorId())).build());
        }

        PoliceStation station = builder.build();
        PoliceStation savedStation = policeStationRepository.save(station);
        return convertToResponseDTO(savedStation);
    }

    @Override
    public PoliceStationResponseDTO getPoliceStationById(UUID id) {
        PoliceStation station = policeStationRepository.findById(id)
                .orElseThrow(() -> new PoliceStationNotFoundException(id));
        return convertToResponseDTO(station);
    }

    @Override
    public List<PoliceStationResponseDTO> getAllPoliceStations() {
        return policeStationRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PoliceStationResponseDTO updatePoliceStation(UUID id, PoliceStationDTO policeStationDTO) {
        // Check if police station exists
        PoliceStation existingStation = policeStationRepository.findById(id)
                .orElseThrow(() -> new PoliceStationNotFoundException(id));

        // Check if station name is being changed to a value that already exists
        if (!existingStation.getStationName().equals(policeStationDTO.getStationName())) {
            PoliceStation stationWithName = policeStationRepository.findByStationName(policeStationDTO.getStationName());
            if (stationWithName != null) {
                throw new PoliceStationAlreadyExistsException(policeStationDTO.getStationName());
            }
        }

        existingStation.setStationName(policeStationDTO.getStationName());
        existingStation.setAddress(Address.builder().id(UUID.fromString(policeStationDTO.getAddressId())).build());

        // Set director if directorId is provided, otherwise set to null
        if (policeStationDTO.getDirectorId() != null && !policeStationDTO.getDirectorId().isBlank()) {
            existingStation.setDirector(PoliceOfficer.builder().id(UUID.fromString(policeStationDTO.getDirectorId())).build());
        } else {
            existingStation.setDirector(null);
        }

        PoliceStation updatedStation = policeStationRepository.save(existingStation);
        return convertToResponseDTO(updatedStation);
    }

    @Override
    public void deletePoliceStation(UUID id) {
        policeStationRepository.deleteById(id);
    }

    private PoliceStationResponseDTO convertToResponseDTO(PoliceStation station) {
        return PoliceStationResponseDTO.builder()
                .id(station.getId())
                .stationName(station.getStationName())
                .addressId(station.getAddress().getId())
                .directorId(station.getDirector() != null ? station.getDirector().getId() : null)
                .build();
    }
}
