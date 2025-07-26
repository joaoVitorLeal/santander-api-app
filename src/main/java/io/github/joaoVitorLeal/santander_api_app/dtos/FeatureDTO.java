package io.github.joaoVitorLeal.santander_api_app.dtos;

import io.github.joaoVitorLeal.santander_api_app.domain.model.Feature;
import jakarta.validation.constraints.NotBlank;

public record FeatureDTO(
        @NotBlank
        String icon,

        @NotBlank
        String description
) {
    public FeatureDTO(Feature entity) {
        this(entity.getIcon(), entity.getDescription());
    }

    public Feature toEntity() {
        Feature entity = new Feature();
        entity.setIcon(this.icon);
        entity.setDescription(this.description);
        return entity;
    }
}
