package io.github.joaoVitorLeal.santander_api_app.mappers;

import io.github.joaoVitorLeal.santander_api_app.domain.model.News;
import io.github.joaoVitorLeal.santander_api_app.dtos.NewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    News toEntity(NewsDTO dto);

    NewsDTO toDTO(News entity);
}
