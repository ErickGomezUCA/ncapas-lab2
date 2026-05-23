package com.ncapas.labo_dos_n_capas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeByPersonResponseDto {
    private LocalDate fecha;
    private String tipoCargo;
    private String descripcion;
    private String nombrePolicia;
    private String codigoPolicia;
    private String nombreAcusador;
    private String duiAcusador;
}