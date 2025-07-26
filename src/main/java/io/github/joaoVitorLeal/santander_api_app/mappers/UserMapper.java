package io.github.joaoVitorLeal.santander_api_app.mappers;

import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                AccountMapper.class,
                CardMapper.class,
                FeatureMapper.class,
                NewsMapper.class
        }
)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "account.id", ignore = true),
            @Mapping(target = "card.id", ignore = true),
            @Mapping(target = "features[].user", ignore = true),
            @Mapping(target = "news[].user", ignore = true)
    })
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDTO(User entity);
}
