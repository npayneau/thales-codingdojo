package com.thales.example.codindojo.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final String firstName;
    private final String lastName;
}
