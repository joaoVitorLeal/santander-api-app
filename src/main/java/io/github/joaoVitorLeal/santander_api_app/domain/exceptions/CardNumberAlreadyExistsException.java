package io.github.joaoVitorLeal.santander_api_app.domain.exceptions;

public class CardNumberAlreadyExistsException extends RuntimeException{

    public CardNumberAlreadyExistsException(String cardNumber) {
        super("Card number already registered: " + cardNumber);
    }

    public CardNumberAlreadyExistsException() {
        super("This Card number already exists.");
    }

    public CardNumberAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
