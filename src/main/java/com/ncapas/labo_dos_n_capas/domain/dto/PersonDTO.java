package com.ncapas.labo_dos_n_capas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotBlank(message = "Name is required")
    @Pattern(
        regexp = "^[a-zA-Z\\s]+$",
        message = "Name must not contain numbers or special characters"
    )
    private String name;

    @NotBlank(message = "DUI is required")
    @Pattern(
        regexp = "^\\d{7}-\\d{1}$",
        message = "DUI must have the format: 01234567-8 (8 digits, hyphen, 1 digit)"
    )
    private String dui;

    // Regex pattern from: https://regex101.com/r/GCo5uC/1
    @NotBlank(message = "Phone is required")
    @Pattern(
        regexp = "\\+?(\\d{1,2})?[ .-]?\\(?(\\d{3})\\)?[ .-]?(\\d{3})[ .-]?(\\d{4})",
        message = "Phone must have the format: +5031234-5678, or 50312345678"
    )
    private String phone;

    // Regex pattern from: https://regex101.com/r/1h0VAg/1
    @NotBlank(message = "Address ID is required")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Address ID must be a valid UUID"
    )
    private String addressId;
}

