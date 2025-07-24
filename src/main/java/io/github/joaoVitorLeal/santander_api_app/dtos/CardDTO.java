package io.github.joaoVitorLeal.santander_api_app.dtos;


import io.github.joaoVitorLeal.santander_api_app.domain.model.Card;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CardDTO(
        @NotBlank
        String number,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = true)
        @DecimalMax(value = "1000000.00", inclusive = false)
        BigDecimal limit
) {
    public CardDTO(Card entity) {
        this(entity.getNumber(), entity.getLimit());
    }



    // ----------------- LEGADO ----------------- //
    public Card toEntityLegacy(CardDTO dto) {
        Card entity = new Card();
        entity.setNumber(dto.number);
        entity.setLimit(dto.limit);
        return entity;
    }
}
