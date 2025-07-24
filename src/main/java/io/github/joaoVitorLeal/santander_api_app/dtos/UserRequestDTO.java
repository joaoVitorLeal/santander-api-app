package io.github.joaoVitorLeal.santander_api_app.dtos;

import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank String cpf,
        @NotNull @Valid AccountDTO account,
        @Valid CardDTO card,
        @NotEmpty @Valid List<FeatureDTO> features,
        @NotEmpty @Valid List<NewsDTO> news
        ) {

        public UserRequestDTO(User entity) {
                this(
                        entity.getName(),
                        entity.getCpf(),

                        // Account obrigatório — lança exceção se for null
                        ofNullable(entity.getAccount())
                                .map(AccountDTO::new)
                                .orElseThrow(IllegalArgumentException::new),

                        // Card obrigatório — idem acima
                        ofNullable(entity.getCard())
                                .map(CardDTO::new)
                                .orElseThrow(IllegalArgumentException::new),

                        // Lista de features — se null, substitui por lista vazia e mapeia
                        ofNullable(entity.getFeatures())
                                .orElse(emptyList())
                                .stream()
                                .map(FeatureDTO::new)
                                .toList(),

                        // Lista de news — idem
                        ofNullable(entity.getNews())
                                .orElse(emptyList())
                                .stream()
                                .map(NewsDTO::new)
                                .toList()
                );
        }

        public User toEntity() {
                User u = new User();
                u.setName(name);
                u.setCpf(cpf);
                u.setAccount(account.toEntity());
                u.setCard(card.toEntity());
                u.setFeatures(features.stream().map(FeatureDTO::toEntity).toList());
                u.setNews(news.stream().map(NewsDTO::toEntity).toList());
                return u;
        }

        // ----------------- LEGADO ----------------- //
        public User toEntityLegacy(UserRequestDTO dto) {
                User entity = new User();
                entity.setName(dto.name);
                entity.setCpf(dto.cpf);

                entity.setAccount(ofNullable(dto.account)
                        .map(accountDto -> accountDto.toEntity(dto.account)) /// LEGADO
                        .orElseThrow(IllegalArgumentException::new));

                entity.setCard(ofNullable(dto.card)
                        .map(cardDto -> cardDto.toEntity(dto.card)) /// LEGADO
                        .orElseThrow(IllegalArgumentException::new));

                entity.setFeatures(ofNullable(dto.features)
                        .orElse(emptyList())
                        .stream()
                        .map(FeatureDTO::toEntity) /* Usando o méto-do toEntity refatorado @see FeatureDTO */
                        .toList());

                entity.setNews(ofNullable(dto.news)
                        .orElse(emptyList())
                        .stream()
                        .map(newsDto -> newsDto.toEntity(newsDto))
                        .toList());

                return entity;
        }
}
