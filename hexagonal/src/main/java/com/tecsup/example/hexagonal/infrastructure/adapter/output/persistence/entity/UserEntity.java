package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastName;

    @Column(name = "secondLastName", nullable = false, length = 100)
    private String secondLastName;

    @Column(name = "documentNumber", length = 8)
    private Integer documentNumber;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phoneNumber", length = 9)
    private Integer phoneNumber;

    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
}
