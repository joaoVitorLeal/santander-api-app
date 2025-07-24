package io.github.joaoVitorLeal.santander_api_app.dtos;

import io.github.joaoVitorLeal.santander_api_app.domain.model.User;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

public record UserResponseDTO (
        Long id,
        String name,
        String cpf,
        AccountDTO account,
        CardDTO card,
        List<FeatureDTO> features,
        List<NewsDTO> news
) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getCpf(),
                new AccountDTO(user.getAccount()),
                new CardDTO(user.getCard()),
                ofNullable(user.getFeatures()).orElse(emptyList()).stream()
                        .map(FeatureDTO::new).toList(),
                ofNullable(user.getNews()).orElse(emptyList()).stream()
                        .map(NewsDTO::new).toList()
        );
    }
}
