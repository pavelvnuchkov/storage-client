package com.alfa.customer_storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность клиента")
public class ClientDto {

    @Schema(description = "Идентификатор пользователя", example = "1", allowableValues = "больше 0")
    private Integer id;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String name;

    @JsonProperty("lastname")
    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    public ClientDto() {
    }

    public ClientDto(Integer id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
