package io.github.joaoVitorLeal.santander_api_app.service.impl;

import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.AccountNumberAlreadyExistsException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.CpfAlreadyRegisteredException;
import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.UserNotFoundException;
import io.github.joaoVitorLeal.santander_api_app.domain.model.Account;
import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import io.github.joaoVitorLeal.santander_api_app.domain.repository.UserRepository;
import io.github.joaoVitorLeal.santander_api_app.domain.validator.UserValidator;
import io.github.joaoVitorLeal.santander_api_app.dtos.FeatureDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.NewsDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;
import io.github.joaoVitorLeal.santander_api_app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserValidator validator;

    public UserServiceImpl(UserRepository repository, UserValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(UserResponseDTO::new)
                .orElseThrow(() -> new UserNotFoundException("Not found user with ID: " + id));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Override
    public void create(UserRequestDTO userToCreate) {
        if (repository.existsByCpf(userToCreate.cpf())) {
            throw new CpfAlreadyRegisteredException(userToCreate.cpf());
        }
        if (repository.existsByAccountNumber(userToCreate.account().number())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }

        User user = userToCreate.toEntity();
        validator.validate(user);
        repository.save(user);
    }

    @Deprecated
    @Override
    public User createAndReturnUserEntity(UserRequestDTO userToCreate) {
        if (repository.existsByAccountNumber(userToCreate.account().number())) {
            throw new AccountNumberAlreadyExistsException("accountNumber", "The Account number '" + userToCreate.account().number() + "' already exists.");
        }
        return repository.save(userToCreate.toEntityLegacy(userToCreate));
    }

    @Override
    public void update(Long id, UserRequestDTO userToUpdate) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("To update, the user must already be registered in the database."));
        // TODO: REFACTOR FOR THE MAPPER
        existingUser.setName(userToUpdate.name());
        existingUser.setCpf(userToUpdate.cpf());
        existingUser.setAccount(userToUpdate.account().toEntity());
        existingUser.setCard(userToUpdate.card().toEntity());
        existingUser.setFeatures(userToUpdate.features()
                .stream()
                .map(FeatureDTO::toEntity)
                .toList()
        );
        existingUser.setNews(userToUpdate.news()
                .stream()
                .map(NewsDTO::toEntity)
                .toList()
        );

        validator.validate(existingUser);
        repository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new UserNotFoundException("Not found user with ID: " + id);
                        }
                );
    }
}





































