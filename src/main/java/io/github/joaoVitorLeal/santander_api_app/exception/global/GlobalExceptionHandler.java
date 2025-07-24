package io.github.joaoVitorLeal.santander_api_app.exception.global;

import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.AccountNumberAlreadyExistsException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.CpfAlreadyRegisteredException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.InvalidFieldsUserException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.UserNotFoundException;
import io.github.joaoVitorLeal.santander_api_app.exception.dtos.ErrorResponseDTO;
import io.github.joaoVitorLeal.santander_api_app.exception.dtos.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex,
            final HttpServletRequest request
    ) {
        logger.error("Validation error at: [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        List<FieldError> errors = ex.getFieldErrors();
        List<ValidationErrorDTO> validationErrors = errors.stream()
                .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponseDTO(
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        "Validation error:",
                        validationErrors,
                        request.getRequestURI())
                );
    }

    @ExceptionHandler(InvalidFieldsUserException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFieldsUserException(
            final InvalidFieldsUserException ex,
            final HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation error:",
                        List.of(new ValidationErrorDTO(ex.getField(), ex.getMessage())),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(AccountNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccountNumberAlreadyExistsException(
            final AccountNumberAlreadyExistsException ex,
            final HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponseDTO.conflict(ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(CpfAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDTO> handleCpfAlreadyRegisteredException(
            final CpfAlreadyRegisteredException ex,
            final HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponseDTO.conflict(ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(
            final UserNotFoundException ex,
            final HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.notFound(ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleUntreatedExceptions(
            final MethodArgumentNotValidException ex,
            final HttpServletRequest request
    ) {
        logger.error("Internal server error at: [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        List<ValidationErrorDTO> validationErrors = ex.getFieldErrors()
                .stream()
                .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "An unexpected error occurred. Please try again later.",
                        List.of(),
                        request.getRequestURI())
                );
    }
}
