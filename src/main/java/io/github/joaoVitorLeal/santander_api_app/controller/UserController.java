package io.github.joaoVitorLeal.santander_api_app.controller;

import io.github.joaoVitorLeal.santander_api_app.controller.util.LocationUriBuilder;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;
import io.github.joaoVitorLeal.santander_api_app.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserRequestDTO userToCreate) {
        UserResponseDTO userCreated = service.create(userToCreate);

        URI location = LocationUriBuilder.build(userCreated.id());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userToUpdate) {
        service.update(id, userToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
