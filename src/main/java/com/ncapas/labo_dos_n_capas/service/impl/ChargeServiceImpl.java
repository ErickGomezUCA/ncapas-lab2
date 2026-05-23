package com.ncapas.labo_dos_n_capas.service.impl;

import com.ncapas.labo_dos_n_capas.domain.dto.ChargeDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.ChargeResponseDTO;
import com.ncapas.labo_dos_n_capas.domain.entity.Charge;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import com.ncapas.labo_dos_n_capas.domain.entity.PoliceOfficer;
import com.ncapas.labo_dos_n_capas.domain.entity.catalogue.ChargeType;
import com.ncapas.labo_dos_n_capas.exception.ChargeAlreadyExistsException;
import com.ncapas.labo_dos_n_capas.exception.ChargeNotFoundException;
import com.ncapas.labo_dos_n_capas.repository.ChargeRepository;
import com.ncapas.labo_dos_n_capas.service.ChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChargeServiceImpl implements ChargeService {
    private final ChargeRepository chargeRepository;

    @Override
    public ChargeResponseDTO createCharge(ChargeDTO chargeDTO) {
        UUID accuserId = UUID.fromString(chargeDTO.getAccuserId());
        UUID accusedId = UUID.fromString(chargeDTO.getAccusedId());

        // Check if charge already exists for these two people
        Person accuser = Person.builder().id(accuserId).build();
        Person accused = Person.builder().id(accusedId).build();
        Charge existingCharge = chargeRepository.findByAccuserAndAccused(accuser, accused);
        if (existingCharge != null) {
            throw new ChargeAlreadyExistsException(accuserId, accusedId);
        }

        Charge charge = Charge.builder()
                .date(chargeDTO.getDate())
                .accuser(accuser)
                .accused(accused)
                .chargeType(ChargeType.valueOf(chargeDTO.getChargeType()))
                .description(chargeDTO.getDescription())
                .policeOfficer(PoliceOfficer.builder().id(UUID.fromString(chargeDTO.getPoliceOfficerId())).build())
                .build();

        Charge savedCharge = chargeRepository.save(charge);
        return convertToResponseDTO(savedCharge);
    }

    @Override
    public ChargeResponseDTO getChargeById(UUID id) {
        Charge charge = chargeRepository.findById(id)
                .orElseThrow(() -> new ChargeNotFoundException(id));
        return convertToResponseDTO(charge);
    }

    @Override
    public List<ChargeResponseDTO> getAllCharges() {
        return chargeRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChargeResponseDTO updateCharge(UUID id, ChargeDTO chargeDTO) {
        // Check if charge exists
        Charge existingCharge = chargeRepository.findById(id)
                .orElseThrow(() -> new ChargeNotFoundException(id));

        existingCharge.setDate(chargeDTO.getDate());
        existingCharge.setAccuser(Person.builder().id(UUID.fromString(chargeDTO.getAccuserId())).build());
        existingCharge.setAccused(Person.builder().id(UUID.fromString(chargeDTO.getAccusedId())).build());
        existingCharge.setChargeType(ChargeType.valueOf(chargeDTO.getChargeType()));
        existingCharge.setDescription(chargeDTO.getDescription());
        existingCharge.setPoliceOfficer(PoliceOfficer.builder().id(UUID.fromString(chargeDTO.getPoliceOfficerId())).build());

        Charge updatedCharge = chargeRepository.save(existingCharge);
        return convertToResponseDTO(updatedCharge);
    }

    @Override
    public void deleteCharge(UUID id) {
        chargeRepository.deleteById(id);
    }

    private ChargeResponseDTO convertToResponseDTO(Charge charge) {
        return ChargeResponseDTO.builder()
                .id(charge.getId())
                .date(charge.getDate())
                .accuserId(charge.getAccuser().getId())
                .accusedId(charge.getAccused().getId())
                .chargeType(charge.getChargeType().toString())
                .description(charge.getDescription())
                .policeOfficerId(charge.getPoliceOfficer().getId())
                .build();
    }
}

