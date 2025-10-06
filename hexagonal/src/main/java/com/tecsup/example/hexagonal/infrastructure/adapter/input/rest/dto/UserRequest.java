package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private Integer phoneNumber;
    private String documentNumber;


}
