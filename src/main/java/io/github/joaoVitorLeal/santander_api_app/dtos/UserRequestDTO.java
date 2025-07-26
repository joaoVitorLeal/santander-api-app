package io.github.joaoVitorLeal.santander_api_app.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "The CPF must contain eleven (11) digits.") String cpf,
        @NotNull @Valid AccountDTO account,
        @Valid CardDTO card,
        @NotEmpty @Valid List<FeatureDTO> features,
        @NotEmpty @Valid List<NewsDTO> news
) { }
