package io.github.joaoVitorLeal.santander_api_app.domain.exceptions;

public class InvalidFieldsUserException extends RuntimeException {

    private final String field;

    public InvalidFieldsUserException() {
        this(null, "Validation error.");
    }

    public InvalidFieldsUserException(String field, String message) {
        super(message);
        this.field = field;
    }

    public InvalidFieldsUserException(String message, Throwable cause) {
        super(message, cause);
        this.field = null;
    }

    public String getField() {
        return this.field;
    }
}
