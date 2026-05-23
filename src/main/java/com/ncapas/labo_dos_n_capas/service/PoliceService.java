package com.ncapas.labo_dos_n_capas.service;

import com.ncapas.labo_dos_n_capas.domain.dto.PoliceDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PoliceResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PoliceService {
    PoliceResponseDTO createPolice(PoliceDTO policeDTO);
    PoliceResponseDTO getPoliceById(UUID id);
    PoliceResponseDTO getPoliceByCode(String code);
    List<PoliceResponseDTO> getAllPolice();
    PoliceResponseDTO updatePolice(UUID id, PoliceDTO policeDTO);
    void deletePolice(UUID id);
}

