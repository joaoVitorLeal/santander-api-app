package io.github.joaoVitorLeal.santander_api_app.service;

import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findAll();

    User create(UserRequestDTO userDto);

    void update(Long id, UserRequestDTO userDto);

    void delete(Long id);
}
