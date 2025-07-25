package io.github.joaoVitorLeal.santander_api_app.mappers;

import io.github.joaoVitorLeal.santander_api_app.domain.model.Card;
import io.github.joaoVitorLeal.santander_api_app.domain.model.Feature;
import io.github.joaoVitorLeal.santander_api_app.dtos.CardDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.FeatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping( target = "id", ignore =true)
    Card toEntity(CardDTO dto);

    CardDTO toDTO(Card entity);
}
