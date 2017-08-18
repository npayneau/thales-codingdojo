package com.thales.example.codindojo.demo.controller;

import com.thales.example.codindojo.demo.dto.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UsersController {

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getUsers(){
        return Stream.of(new User("Payneau","Nicolas"),new User("Oger","Yann")).collect(Collectors.toList());
    }
}
