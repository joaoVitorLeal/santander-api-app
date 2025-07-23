package io.github.joaoVitorLeal.santander_api_app.domain.model;

import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class News extends BaseItem {

    @Serial
    private static final long serialVersionUID = 1L;

    public News() { }
}
