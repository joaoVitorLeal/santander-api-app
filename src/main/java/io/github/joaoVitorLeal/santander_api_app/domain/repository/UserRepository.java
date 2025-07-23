package io.github.joaoVitorLeal.santander_api_app.domain.repository;

import io.github.joaoVitorLeal.santander_api_app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
