package com.tecsup.example.hexagonal.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String passwordRaw = "admin123";
        String passwordEncoded = encoder.encode(passwordRaw);
        //log.info("{} -> {}", passwordRaw, passwordEncoded);
        System.out.println(passwordEncoded);
    }
}
