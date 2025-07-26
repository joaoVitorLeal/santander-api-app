package io.github.joaoVitorLeal.santander_api_app.service.impl;

import io.github.joaoVitorLeal.santander_api_app.domain.exceptions.UserNotFoundException;
import io.github.joaoVitorLeal.santander_api_app.domain.model.Account;
import io.github.joaoVitorLeal.santander_api_app.domain.model.Card;
import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import io.github.joaoVitorLeal.santander_api_app.domain.repository.UserRepository;
import io.github.joaoVitorLeal.santander_api_app.domain.validator.UserValidator;
import io.github.joaoVitorLeal.santander_api_app.dtos.FeatureDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.NewsDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;
import io.github.joaoVitorLeal.santander_api_app.mappers.UserMapper;
import io.github.joaoVitorLeal.santander_api_app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserValidator validator;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserValidator validator, UserMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(UserResponseDTO::new)
                .orElseThrow(() -> new UserNotFoundException("Not found user with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO userToCreate) {
        if (repository.existsByAccountNumber(userToCreate.account().number())) {
            throw new AccountNumberAlreadyExistsException("This Account number already exists.");
        }

        User user = mapper.toEntity(userToCreate);
        validator.validate(user);
        return mapper.toDTO(repository.save(user));
    }

    @Transactional
    public void update(Long id, UserRequestDTO userToUpdate) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("To update, the user must already be registered in the database."));

        // Conversão temporária para validação de dados
        User userToValidate = mapper.toEntity(userToUpdate);
        userToValidate.setId(existingUser.getId());

        // Validar campos
        validator.validate(userToValidate);

        // Atualizar Campos
        updateUserFields(existingUser, userToUpdate);

        // Salvar
        repository.save(existingUser);
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new UserNotFoundException("Not found user with ID: " + id);
                        }
                );
    }

    private void updateUserFields(User existingUser, UserRequestDTO userToUpdate) {
        existingUser.setName(userToUpdate.name());
        existingUser.setCpf(userToUpdate.cpf());

        // Atualiza os campos da Account sem substituir a entidade
        Account existingAccount = existingUser.getAccount();
        Account incomingAccount = userToUpdate.account().toEntity();

        existingAccount.setNumber(incomingAccount.getNumber());
        existingAccount.setAgency(incomingAccount.getAgency());
        existingAccount.setBalance(incomingAccount.getBalance());
        existingAccount.setLimit(incomingAccount.getLimit());

        // Atualiza os campos da Card sem substituir a entidade
        Card existingCard = existingUser.getCard();
        Card incomingCard = userToUpdate.card().toEntity();

        existingCard.setNumber(incomingCard.getNumber());
        existingCard.setLimit(incomingCard.getLimit());

        // Substitui completamente as coleções
        existingUser.setFeatures(userToUpdate.features()
                .stream()
                .map(FeatureDTO::toEntity)
                .collect(Collectors.toList()));

        existingUser.setNews(userToUpdate.news()
                .stream()
                .map(NewsDTO::toEntity)
                .collect(Collectors.toList()));
    }
}
