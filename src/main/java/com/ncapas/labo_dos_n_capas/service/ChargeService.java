package com.ncapas.labo_dos_n_capas.service;

import com.ncapas.labo_dos_n_capas.domain.dto.ChargeDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.ChargeResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ChargeService {
    ChargeResponseDTO createCharge(ChargeDTO chargeDTO);
    ChargeResponseDTO getChargeById(UUID id);
    List<ChargeResponseDTO> getAllCharges();
    ChargeResponseDTO updateCharge(UUID id, ChargeDTO chargeDTO);
    void deleteCharge(UUID id);
}

