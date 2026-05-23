package com.ncapas.labo_dos_n_capas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonChargesResponseDto {
    private PersonBasicResponseDto persona;
    private List<ChargeByPersonResponseDto> cargos;
}