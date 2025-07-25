package io.github.joaoVitorLeal.santander_api_app.mappers;

import io.github.joaoVitorLeal.santander_api_app.domain.model.Account;
import io.github.joaoVitorLeal.santander_api_app.dtos.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping( target = "id", ignore =true)
    Account toEntity(AccountDTO dto);

    AccountDTO toDTO(Account entity);
}
