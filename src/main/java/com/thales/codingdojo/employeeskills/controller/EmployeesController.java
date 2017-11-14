package com.thales.codingdojo.employeeskills.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thales.codingdojo.employeeskills.domain.Employee;
import com.thales.codingdojo.employeeskills.domain.Skill;
import com.thales.codingdojo.employeeskills.service.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeesController {

    @Value("${thales.properties.pOne}")
    public String propertyOne;

    @Value("${thales.properties.pTwo}")
    public String propertyTwo;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @GetMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> readEmployees(){
        log.debug("Get employees with properties: {} {} {}", propertyOne, propertyTwo, "Test");
        return StreamSupport.stream(employeeRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/employees", params = {"lastName", "firstName"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> searchEmployees(@RequestParam(value="lastName") String lastName, @RequestParam(value="firstName") String firstName, Sort sort){
        log.debug("Search employees by lastname or firstname: {} {}", lastName, firstName);
		return employeeRepository.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName, sort);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/employees", params = "lastName", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> searchEmployees(@RequestParam(value="lastName") String lastName, Sort sort){
        log.debug("Search employees with lastname starting with : {}", lastName);
		return employeeRepository.findByLastNameStartingWith(lastName, sort);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/employees", params = "birthDateBefore", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> searchEmployees(@RequestParam(value="birthDateBefore", required = false) Date birthDate, Pageable pageable){
        log.debug("Count employees with birth date earlier than : {}", birthDate);
		return employeeRepository.getByBirthDateBefore(birthDate, pageable);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/employees/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Employee readEmployee(@PathVariable(value="id") Long id){
        log.debug("Get on employee: {}", id);
        return employeeRepository.findOne(id);
    }
    
    @Transactional(readOnly = true)
    @GetMapping(value = "/employees/{id}/skills", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Set<Skill> readEmployeeSkills(@PathVariable(value="id") Long id){
        log.debug("Get on employee: {}", id);
        return employeeRepository.findOne(id).getSkills();
    }

    @Transactional
    @PostMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Employee createEmployee(@RequestBody Employee employee){
        log.debug("Create employee: {} {} {}", employee.getFirstName(), employee.getLastName(), employee.getBirthDate());
        return employeeRepository.save(employee);
    }

    @Transactional
    @PutMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Employee updateEmployee(@RequestBody Employee employee){
        log.debug("Update employee: {}", employee.getId());
        return employeeRepository.save(employee);
    }

    @Transactional
    @DeleteMapping(value = "/employees/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteEmployee(@RequestBody Employee employee){
        log.debug("Delete employee: {}", employee.getId());
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
