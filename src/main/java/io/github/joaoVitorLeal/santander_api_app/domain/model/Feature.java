package io.github.joaoVitorLeal.santander_api_app.domain.model;

import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class Feature extends BaseItem {

    @Serial
    private static final long serialVersionUID = 1L;

    public Feature() { }
}
