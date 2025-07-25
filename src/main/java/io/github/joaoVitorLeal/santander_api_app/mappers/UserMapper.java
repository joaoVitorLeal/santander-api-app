package io.github.joaoVitorLeal.santander_api_app.mappers;

import io.github.joaoVitorLeal.santander_api_app.domain.model.*;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Account.class, Card.class, Feature.class, News.class})
public interface UserMapper {


    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDTO(User user);
}
