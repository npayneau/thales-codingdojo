package com.thales.example.codingdojo.demoemployee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private final String firstName;
    private final String lastName;
}
