package io.github.joaoVitorLeal.santander_api_app.dtos;

import io.github.joaoVitorLeal.santander_api_app.domain.model.Account;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record AccountDTO(
        @NotBlank
        String number,

        @NotBlank
        String agency,

        @NotNull()
        @DecimalMin(value = "-10000.00", inclusive = false)
        BigDecimal balance,

        @NotNull
        @PositiveOrZero
        @DecimalMax(value = "99999999999.99")
        BigDecimal limit
) {
    public AccountDTO(Account entity) {
        this(entity.getNumber(), entity.getAgency(), entity.getBalance(), entity.getLimit());
    }

    public Account toEntity() {
        Account account = new Account();
        account.setNumber(this.number);
        account.setAgency(this.agency);
        account.setBalance(this.balance);
        account.setLimit(this.limit);
        return account;
    }
}
