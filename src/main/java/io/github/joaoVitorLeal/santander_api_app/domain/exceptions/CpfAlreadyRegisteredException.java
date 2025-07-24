package io.github.joaoVitorLeal.santander_api_app.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CpfAlreadyRegisteredException extends RuntimeException {

    public CpfAlreadyRegisteredException() {
        super("CPF already registered.");
    }

    public CpfAlreadyRegisteredException(String cpf) {
        super("CPF already registered: " + cpf);
    }

    public CpfAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
