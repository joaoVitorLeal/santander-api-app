package io.github.joaoVitorLeal.santander_api_app.service;

import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;

public interface UserService extends CrudService<UserRequestDTO, UserResponseDTO, Long> {
}
