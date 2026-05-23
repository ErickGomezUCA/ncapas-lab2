package com.ncapas.labo_dos_n_capas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeResponseDTO {
    private UUID id;
    private LocalDate date;
    private UUID accuserId;
    private UUID accusedId;
    private String chargeType;
    private String description;
    private UUID policeOfficerId;
}

