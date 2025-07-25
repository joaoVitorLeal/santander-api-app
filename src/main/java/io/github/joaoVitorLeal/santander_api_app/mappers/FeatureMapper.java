package io.github.joaoVitorLeal.santander_api_app.mappers;

import io.github.joaoVitorLeal.santander_api_app.domain.model.Feature;
import io.github.joaoVitorLeal.santander_api_app.dtos.FeatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureMapper {

    @Mapping( target = "id", ignore =true)
    Feature toEntity(FeatureDTO dto);

    FeatureDTO toDTO(Feature entity);
}
