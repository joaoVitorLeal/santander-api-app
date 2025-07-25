package io.github.joaoVitorLeal.santander_api_app.service;

import java.util.List;

/**
 * Interface genérica para serviço CRUD em recursos utilizando DTO.
 *
 * @param <REQ> Tipo do DTO de requisição da entidade
 * @param <RES> Tipo do DTO de resposta da entidade
 * @param <ID>   Tipo do identificador da entidade
 */

public interface CrudService <REQ, RES, ID>{

    List<RES> findAll();
    RES findById(ID id);
    RES create(REQ entity);
    void update(ID id, REQ entity);
    void delete(ID id);
}
