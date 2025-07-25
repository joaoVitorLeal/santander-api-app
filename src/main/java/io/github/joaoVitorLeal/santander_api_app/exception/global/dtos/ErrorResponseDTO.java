package io.github.joaoVitorLeal.santander_api_app.exception.global.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Omitir os valores null automaticamente ao serializar o objeto em JSON.
public record ErrorResponseDTO(
        Instant timestamp,
        int status,
        String message,
        List<ValidationErrorDTO> errors,
        String path
) {

    public ErrorResponseDTO(int status, String message, List<ValidationErrorDTO> errors, String path) {
        this(Instant.now(), status, message, errors, path);
    }

    public static ErrorResponseDTO standardResponse(String message, String path) {
        return new ErrorResponseDTO(Instant.now(), HttpStatus.BAD_REQUEST.value(), message, List.of(), path);
    }

    public static ErrorResponseDTO conflict(String message, String path) {
        return new ErrorResponseDTO(Instant.now(), HttpStatus.CONFLICT.value(), message, List.of(), path);
    }

    public static ErrorResponseDTO notFound(String message, String path) {
        return new ErrorResponseDTO(Instant.now(), HttpStatus.NOT_FOUND.value(), message, List.of(), path);
    }
}
