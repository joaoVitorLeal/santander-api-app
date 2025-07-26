package io.github.joaoVitorLeal.santander_api_app.domain.validator;

import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.AccountNumberAlreadyExistsException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.CardNumberAlreadyExistsException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.CpfAlreadyRegisteredException;
import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import io.github.joaoVitorLeal.santander_api_app.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository repository;

    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    public void validate(User userToValidate) {
        validateCpf(userToValidate);
        validateAccountNumber(userToValidate);
        validateCardNumber(userToValidate);
    }

    private void validateCpf(User userToValidade) {
        repository.findByCpf(userToValidade.getCpf()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(userToValidade.getId())) {
                throw new CpfAlreadyRegisteredException(userToValidade.getCpf());
            }
        });
    }

    private void validateAccountNumber(User userToValidade) {
        repository.findByAccountNumber(userToValidade.getAccount().getNumber())
                .ifPresent(existingUser -> {
            if (!existingUser.getId().equals(userToValidade.getId())) {
                throw new AccountNumberAlreadyExistsException(userToValidade.getAccount().getNumber());
            }
        });
    }

    private void validateCardNumber(User userToValidade) {
        repository.findByCardNumber(userToValidade.getCard().getNumber()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(userToValidade.getId())) {
                throw new CardNumberAlreadyExistsException(userToValidade.getCard().getNumber());
            }
        });
    }
}
