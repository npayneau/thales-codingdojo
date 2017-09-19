package com.thales.example.codingdojo.demoskill.controller;

import com.thales.example.codingdojo.demoskill.dto.Skill;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class SkillsController {

    @GetMapping(value = "/skills", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Skill> getSkills(){
        return Stream.of(new Skill("Java", "Java language", "language"),new Skill("Testlink","Testlink software", "Validation")).collect(Collectors.toList());
    }
}
