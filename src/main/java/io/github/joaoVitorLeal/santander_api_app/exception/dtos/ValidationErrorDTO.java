package io.github.joaoVitorLeal.santander_api_app.exception.dtos;

public class ValidationErrorDTO {

    private final String field;
    private final String error;

    public ValidationErrorDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public String getField() {
        return field;
    }
}
