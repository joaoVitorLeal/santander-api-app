package io.github.joaoVitorLeal.santander_api_app.domain.validator;

import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.CpfAlreadyRegisteredException;
import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import io.github.joaoVitorLeal.santander_api_app.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {

    private final UserRepository repository;

    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    public void validate(User userToValidate) {
        if (isUserRegistered(userToValidate)) {
            throw new CpfAlreadyRegisteredException(userToValidate.getCpf());
        }
    }

    private boolean isUserRegistered(User userToValidate) {
        Optional<User> possibleUser = repository.findByCpf(userToValidate.getCpf());

        if (userToValidate.getId() == null) {
            return possibleUser.isPresent();
        }

        return possibleUser
                .map(foundUser -> !foundUser.getId().equals(userToValidate.getId())) // true -> se possuir ID's diferentes mas mesmo CPF (registro duplicado)
                .orElse(false);
    }
}
