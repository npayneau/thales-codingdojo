package com.thales.codingdojo.employeeskills.service;


import com.thales.codingdojo.employeeskills.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastname);
    int countByLastNameIgnoreCase(String lastname);
}
