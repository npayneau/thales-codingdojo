package com.thales.codingdojo.employeeskills.service;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thales.codingdojo.employeeskills.domain.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
	
	List<Skill> findByNameContainingIgnoreCase(final String name);
}
