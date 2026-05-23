package com.ncapas.labo_dos_n_capas.service.impl;

import com.ncapas.labo_dos_n_capas.domain.dto.PoliceDTO;
import com.ncapas.labo_dos_n_capas.domain.dto.response.PoliceResponseDTO;
import com.ncapas.labo_dos_n_capas.domain.entity.PoliceOfficer;
import com.ncapas.labo_dos_n_capas.domain.entity.Person;
import com.ncapas.labo_dos_n_capas.domain.entity.PoliceStation;
import com.ncapas.labo_dos_n_capas.exception.PoliceAlreadyExistsException;
import com.ncapas.labo_dos_n_capas.exception.PoliceNotFoundException;
import com.ncapas.labo_dos_n_capas.repository.PoliceRepository;
import com.ncapas.labo_dos_n_capas.service.PoliceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoliceServiceImpl implements PoliceService {
    private final PoliceRepository policeRepository;

    @Override
    public PoliceResponseDTO createPolice(PoliceDTO policeDTO) {
        // Check if police officer already exists by code
        PoliceOfficer existingPolice = policeRepository.findByCode(policeDTO.getCode());
        if (existingPolice != null) {
            throw new PoliceAlreadyExistsException(policeDTO.getCode());
        }

        PoliceOfficer police = PoliceOfficer.builder()
                .code(policeDTO.getCode())
                .badgeNumber(policeDTO.getBadgeNumber())
                .person(Person.builder().id(UUID.fromString(policeDTO.getPersonId())).build())
                .policeStation(PoliceStation.builder().id(UUID.fromString(policeDTO.getPoliceStationId())).build())
                .build();

        PoliceOfficer savedPolice = policeRepository.save(police);
        return convertToResponseDTO(savedPolice);
    }

    @Override
    public PoliceResponseDTO getPoliceById(UUID id) {
        PoliceOfficer police = policeRepository.findById(id)
                .orElseThrow(() -> new PoliceNotFoundException(id));
        return convertToResponseDTO(police);
    }

    @Override
    public PoliceResponseDTO getPoliceByCode(String code) {
        PoliceOfficer police = policeRepository.findByCode(code);
        if (police == null) {
            throw new PoliceNotFoundException(code);
        }
        return convertToResponseDTO(police);
    }

    @Override
    public List<PoliceResponseDTO> getAllPolice() {
        return policeRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PoliceResponseDTO updatePolice(UUID id, PoliceDTO policeDTO) {
        // Check if police officer exists
        PoliceOfficer existingPolice = policeRepository.findById(id)
                .orElseThrow(() -> new PoliceNotFoundException(id));

        // Check if code is being changed to a value that already exists
        if (!existingPolice.getCode().equals(policeDTO.getCode())) {
            PoliceOfficer policeWithCode = policeRepository.findByCode(policeDTO.getCode());
            if (policeWithCode != null) {
                throw new PoliceAlreadyExistsException(policeDTO.getCode());
            }
        }

        existingPolice.setCode(policeDTO.getCode());
        existingPolice.setBadgeNumber(policeDTO.getBadgeNumber());
        existingPolice.setPerson(Person.builder().id(UUID.fromString(policeDTO.getPersonId())).build());
        existingPolice.setPoliceStation(PoliceStation.builder().id(UUID.fromString(policeDTO.getPoliceStationId())).build());

        PoliceOfficer updatedPolice = policeRepository.save(existingPolice);
        return convertToResponseDTO(updatedPolice);
    }

    @Override
    public void deletePolice(UUID id) {
        policeRepository.deleteById(id);
    }

    private PoliceResponseDTO convertToResponseDTO(PoliceOfficer police) {
        return PoliceResponseDTO.builder()
                .id(police.getId())
                .code(police.getCode())
                .badgeNumber(police.getBadgeNumber())
                .personId(police.getPerson().getId())
                .policeStationId(police.getPoliceStation().getId())
                .build();
    }
}

