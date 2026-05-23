package com.ncapas.labo_dos_n_capas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithChargesResponseDto {
    private UUID id;
    private String nombre;
    private String dui;
    private String telefono;
    private Long cantidadCargos;
}