package com.ncapas.labo_dos_n_capas.exception;

import java.time.LocalDateTime;

public record CustomErrorResponse(
        LocalDateTime timestamp,
        String message
) {
}
