package com.thales.example.codingdojo.demoskill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Skill {
    private final String name;
    private final String description;
    private final String group;
}
