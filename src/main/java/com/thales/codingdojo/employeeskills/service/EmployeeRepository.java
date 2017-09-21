package com.thales.codingdojo.employeeskills.service;


import com.thales.codingdojo.employeeskills.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
