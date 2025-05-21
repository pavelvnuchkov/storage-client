package com.alfa.customer_storage.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Api хранение клиентов в БД",
                description = "API хранение клиентов и их контактов",
                version = "1.0.0",
                contact = @Contact(
                        name = "Внучков Павел",
                        email = "pavelvnuckov@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
