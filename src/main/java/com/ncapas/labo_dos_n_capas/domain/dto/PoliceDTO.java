package com.ncapas.labo_dos_n_capas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceDTO {

    @NotBlank(message = "Code is required")
    @Pattern(
        regexp = "^[A-Z0-9-]+$",
        message = "Code must be alphanumeric characters and hyphens only"
    )
    private String code;

    @NotBlank(message = "Badge number is required")
    @Pattern(
        regexp = "^[A-Z0-9-]+$",
        message = "Badge number must be alphanumeric characters and hyphens only"
    )
    private String badgeNumber;

    // Regex pattern from: https://regex101.com/r/1h0VAg/1
    @NotBlank(message = "Person ID is required")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Person ID must be a valid UUID"
    )
    private String personId;

    // Regex pattern from: https://regex101.com/r/1h0VAg/1
    @NotBlank(message = "Police Station ID is required")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Police Station ID must be a valid UUID"
    )
    private String policeStationId;
}
