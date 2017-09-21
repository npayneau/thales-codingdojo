package com.thales.codingdojo.employeeskills.service;


import com.thales.codingdojo.employeeskills.domain.Employee;
import com.thales.codingdojo.employeeskills.domain.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface SkillRepository extends CrudRepository<Skill, Long> {
}
