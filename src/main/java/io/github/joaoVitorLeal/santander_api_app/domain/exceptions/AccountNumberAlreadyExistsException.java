package io.github.joaoVitorLeal.santander_api_app.domain.exceptions;

public class AccountNumberAlreadyExistsException extends RuntimeException{

    private final String field;

    public AccountNumberAlreadyExistsException(String message) {
        super(message);
        this.field = null;
    }

    public AccountNumberAlreadyExistsException(String field, String message) {
        super(message);
        this.field = field;
    }

    public AccountNumberAlreadyExistsException() {
        super("This Account number already exists.");
        this.field = "accountNumber";
    }

    public AccountNumberAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
        this.field = null;
    }

    public String getField() {
        return field;
    }
}
