package com.ncapas.labo_dos_n_capas.service;

import com.ncapas.labo_dos_n_capas.domain.dto.PoliceStationDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PoliceStationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PoliceStationService {
    PoliceStationResponseDTO createPoliceStation(PoliceStationDTO policeStationDTO);
    PoliceStationResponseDTO getPoliceStationById(UUID id);
    List<PoliceStationResponseDTO> getAllPoliceStations();
    PoliceStationResponseDTO updatePoliceStation(UUID id, PoliceStationDTO policeStationDTO);
    void deletePoliceStation(UUID id);
}

