package io.github.joaoVitorLeal.santander_api_app.configs;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = { @Server(url = "/", description = "Default server URL") }, // CORS definition for Swagger
        info = @Info(
                title = "Santander Open Academy — GoF with Spring Boot — REST API Demonstration",
                version = "1.1.0",
                description = """
            This project demonstrates  developed as part of the DIO and Santander Open Academy initiative.
            Source code: https://github.com/joaoVitorLeal/santander-api-app
            """,
                termsOfService = "https://opensource.org/licenses/MIT",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                contact = @Contact(
                        name = "João Leal",
                        email = "joaoleal98@outlook.com",
                        url = "https://github.com/joaoVitorLeal"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "LinkedIn — Professional Profile",
                url = "https://linkedin.com/in/joaovlc"
        )
)
public class OpenApiConfig {
}
