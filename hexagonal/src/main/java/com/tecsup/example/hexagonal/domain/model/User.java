package com.tecsup.example.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private  Long id;
    private String name;
    private String email;
    private String lastName;

    // Metodos de negocios
    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() > 5;
    }

    public boolean hasValidName() {
        return name != null &&
                !name.trim().isEmpty() &&
                name.length() >= 2;
    }

    public boolean hasValidatelastName() {
        return lastName != null &&
                !lastName.trim().isEmpty() &&
                lastName.length() >= 2;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', lastname= '" + lastName + "', email='" + email + "'}";
    }

}
