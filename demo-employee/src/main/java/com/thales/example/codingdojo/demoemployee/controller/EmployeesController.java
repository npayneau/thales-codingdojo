package com.thales.example.codingdojo.demoemployee.controller;

import com.thales.example.codingdojo.demoemployee.dto.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class EmployeesController {

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Employee> getEmployees(){
        return Stream.of(new Employee("Payneau","Nicolas"),new Employee("Oger","Yann")).collect(Collectors.toList());
    }
}
