package com.alfa.customer_storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность контакта")
public class ContactDto {

    @Schema(description = "Телефон клиента", example = "+79273332211")
    private String phone;

    @Schema(description = "Email клиента", example = "ivan@ivan.ru")
    private String email;

    public ContactDto() {
    }

    public ContactDto(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
