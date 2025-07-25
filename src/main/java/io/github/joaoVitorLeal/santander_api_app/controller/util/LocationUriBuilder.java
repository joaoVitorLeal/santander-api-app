package io.github.joaoVitorLeal.santander_api_app.controller.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class LocationUriBuilder {

    private LocationUriBuilder() { }

    /**
     * Gera a URI de localização padrão para um recurso recém-criado,
     * usando o ID informado e o caminho da requisição atual.
     *
     * @param id ID do recurso
     */
    public static URI build(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
