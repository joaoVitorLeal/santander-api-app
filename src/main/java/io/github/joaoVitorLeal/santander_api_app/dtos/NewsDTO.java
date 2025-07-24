package io.github.joaoVitorLeal.santander_api_app.dtos;

import io.github.joaoVitorLeal.santander_api_app.domain.model.News;
import jakarta.validation.constraints.NotBlank;

public record NewsDTO(
        @NotBlank
        String icon,

        @NotBlank
        String description
) {
    public NewsDTO(News entity) {
        this(entity.getIcon(), entity.getDescription());
    }

    public static News toEntity(NewsDTO dto) {
        News entity = new News();
        entity.setIcon(dto.icon);
        entity.setDescription(dto.description);
        return entity;
    }
}
