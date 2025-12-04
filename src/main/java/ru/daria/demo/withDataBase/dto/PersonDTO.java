package ru.daria.demo.withDataBase.dto;

import lombok.Data;

@Data //геттеры и сеттеры, стринг и хэшкод - все методы сразу
public class PersonDTO {
    private String username;
    private String email;

    public PersonDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public PersonDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
