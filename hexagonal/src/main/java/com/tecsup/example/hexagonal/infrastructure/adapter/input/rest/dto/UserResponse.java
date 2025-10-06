package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private Integer phoneNumber;
    private String documentNumber;
    private String email;

}
