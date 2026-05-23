package com.ncapas.labo_dos_n_capas.exception;

import com.ncapas.labo_dos_n_capas.domain.dto.GenericResponse;
import com.ncapas.labo_dos_n_capas.utils.ErrorTool;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorTool errorTool;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return GenericResponse.builder()
                .data(errorTool.mapErrors(ex.getBindingResult().getFieldErrors()))
                .status(HttpStatus.BAD_REQUEST)
                .build().buildResponse();

    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<GenericResponse> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                ex.getMessage()
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.CONFLICT)
                .build()
                .buildResponse();

    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePersonNotFoundException(PersonNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                ex.getMessage()
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.NOT_FOUND)
                .build()
                .buildResponse();

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GenericResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Constraint violation: " + ex.getMostSpecificCause().getMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                message
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.CONFLICT)
                .build()
                .buildResponse();
    }

    @ExceptionHandler(PoliceAlreadyExistsException.class)
    public ResponseEntity<GenericResponse> handlePoliceAlreadyExistsException(PoliceAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                ex.getMessage()
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.CONFLICT)
                .build()
                .buildResponse();
    }

    @ExceptionHandler(PoliceNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePoliceNotFoundException(PoliceNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                ex.getMessage()
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.NOT_FOUND)
                .build()
                .buildResponse();
    }

    @ExceptionHandler(PoliceStationAlreadyExistsException.class)
    public ResponseEntity<GenericResponse> handlePoliceStationAlreadyExistsException(PoliceStationAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                ex.getMessage()
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.CONFLICT)
                .build()
                .buildResponse();
    }

    @ExceptionHandler(PoliceStationNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePoliceStationNotFoundException(PoliceStationNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                java.time.LocalDateTime.now(),
                ex.getMessage()
        );
        return GenericResponse.builder()
                .data(errorResponse)
                .status(HttpStatus.NOT_FOUND)
                .build()
                .buildResponse();
    }
}
