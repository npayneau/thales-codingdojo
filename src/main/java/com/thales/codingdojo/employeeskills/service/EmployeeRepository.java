package com.thales.codingdojo.employeeskills.service;


import com.thales.codingdojo.employeeskills.domain.Employee;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByLastName(String lastname);

    List<Employee> findByLastNameOrFirstNameAllIgnoreCase(String lastname, String firstname, Sort sort);

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastname);
    
    int countByLastNameIgnoreCase(String lastname);
    
    List<Employee> getByBirthDateBefore(Date birthDate, Pageable pageable);
    
    Employee findFirstByOrderByLastNameAsc();
    
    List<Employee> findTop10ByLastName(String lastname, Pageable pageable);

    @Query("select e from Employee e where e.lastName like ?1%")
    List<Employee> findByLastNameStartingWith(String lastName, Sort sort);
    
}
