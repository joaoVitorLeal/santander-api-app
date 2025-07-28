package io.github.joaoVitorLeal.santander_api_app.controller;

import io.github.joaoVitorLeal.santander_api_app.controller.util.LocationUriBuilder;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserRequestDTO;
import io.github.joaoVitorLeal.santander_api_app.dtos.UserResponseDTO;
import io.github.joaoVitorLeal.santander_api_app.exception.global.dtos.ErrorResponseDTO;
import io.github.joaoVitorLeal.santander_api_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User", description = "RESTful API for managing users.")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operation successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Example response",
                                    value = """
                                            {
                                              "id": 1,
                                              "name": "John Doe",
                                              "cpf": "00000011111",
                                              "account": {
                                                "number": "1234-56",
                                                "agency": "123456-7",
                                                "balance": 800.00,
                                                "limit": 5000.00
                                              },
                                              "card": {
                                                "number": "1111 2222 3333 4444",
                                                "limit": 2500.50
                                              },
                                              "features": [
                                                {
                                                  "icon": "https://some.feature.icon.com/png",
                                                  "description": "Feature description"
                                                }
                                              ],
                                              "news": [
                                                {
                                                  "icon": "https://some.news.icon.com/png",
                                                  "description": "News description"
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error",
                    content = @Content()
            )
    })
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Create a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Example request",
                                    value = """
                                            {
                                              "name": "John Doe",
                                              "cpf": "00000011111",
                                              "account": {
                                                "number": "1234-56",
                                                "agency": "123456-7",
                                                "balance": 800.00,
                                                "limit": 5000.00
                                              },
                                              "card": {
                                                "number": "1111 2222 3333 4444",
                                                "limit": 2500.50
                                              },
                                              "features": [
                                                {
                                                  "icon": "https://some.feature.icon.com/png",
                                                  "description": "Feature description"
                                                }
                                              ],
                                              "news": [
                                                {
                                                  "icon": "https://some.news.icon.com/png",
                                                  "description": "News description"
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Operation successful"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided"),
            @ApiResponse(responseCode = "409", description = "Conflict: CPF, account number, or card number already registered"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> create(@Valid @RequestBody UserRequestDTO userToCreate) {
        UserResponseDTO userCreated = service.create(userToCreate);

        URI location = LocationUriBuilder.build(userCreated.id());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operation successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "array",
                                    implementation = UserResponseDTO.class
                            ),
                            examples = @ExampleObject(
                                    name = "Example response",
                                    value = """
                                            [
                                              {
                                                "id": 1,
                                                "name": "John Doe",
                                                "cpf": "00000011111",
                                                "account": {
                                                  "number": "1234-56",
                                                  "agency": "123456-7",
                                                  "balance": 800.00,
                                                  "limit": 5000.00
                                                },
                                                "card": {
                                                  "number": "1111 2222 3333 4444",
                                                  "limit": 2500.50
                                                },
                                                "features": [
                                                  {
                                                    "icon": "https://some.feature.icon.com/png",
                                                    "description": "Feature description"
                                                  }
                                                ],
                                                "news": [
                                                  {
                                                    "icon": "https://some.news.icon.com/png",
                                                    "description": "News description"
                                                  }
                                                ]
                                              },
                                              {
                                                "id": 2,
                                                "name": "Jane Smith",
                                                "cpf": "22233344455",
                                                "account": {
                                                  "number": "6543-21",
                                                  "agency": "765432-1",
                                                  "balance": 1500.75,
                                                  "limit": 7200.45
                                                },
                                                "card": {
                                                  "number": "5555 6666 7777 8888",
                                                  "limit": 3500.99
                                                },
                                                "features": [
                                                  {
                                                    "icon": "https://another.feature.icon.com/png",
                                                    "description": "Another feature description"
                                                  }
                                                ],
                                                "news": [
                                                  {
                                                    "icon": "https://another.news.icon.com/png",
                                                    "description": "Another news description"
                                                  }
                                                ]
                                              }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Unexpected server error", content = @Content( ))
    })
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Example request",
                                    value = """
                                            {
                                              "name": "John Doe",
                                              "cpf": "00000011111",
                                              "account": {
                                                "number": "1234-56",
                                                "agency": "123456-7",
                                                "balance": 800.00,
                                                "limit": 5000.00
                                              },
                                              "card": {
                                                "number": "1111 2222 3333 4444",
                                                "limit": 2500.55
                                              },
                                              "features": [
                                                {
                                                  "icon": "https://some.feature.icon.com/png",
                                                  "description": "Feature description"
                                                }
                                              ],
                                              "news": [
                                                {
                                                  "icon": "https://some.news.icon.com/png",
                                                  "description": "News description"
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided"),
            @ApiResponse(responseCode = "409", description = "Conflict: CPF, account number, or card number already registered"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userToUpdate) {
        service.update(id, userToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
