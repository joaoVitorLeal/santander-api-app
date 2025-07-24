package io.github.joaoVitorLeal.santander_api_app.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(){
        super("User not found.");
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
