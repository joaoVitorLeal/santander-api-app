package io.github.joaoVitorLeal.santander_api_app.domain.repository;

import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(String cpf);

    Optional<User> findByAccountNumber(String accountNumber);

    Optional<User> findByCardNumber(String cardNumber);
}
