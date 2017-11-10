package com.thales.codingdojo.employeeskills.controller;

import com.thales.codingdojo.employeeskills.domain.Employee;
import com.thales.codingdojo.employeeskills.domain.Skill;
import com.thales.codingdojo.employeeskills.domain.SkillGroup;
import com.thales.codingdojo.employeeskills.service.EmployeeRepository;
import com.thales.codingdojo.employeeskills.service.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@Slf4j
public class SkillsController {

    @Autowired
    private SkillRepository skillRepository;

    @Transactional(readOnly = true)
    @GetMapping(value = "/skills", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public List<Skill> getSkills(){
        log.debug("Get skills with properties: {} {} {}");
        return StreamSupport.stream(skillRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/skills/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Skill readSkill(@PathVariable(value="id") Long id){
        log.debug("Get on skill: {}", id);
        return skillRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/skills/{id}/employees", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Set<Employee> readSkillEmployees(@PathVariable(value="id") Long id){
        log.debug("Get employees on skill: {}", id);
        return skillRepository.findOne(id).getEmployees();
    }

    @Transactional
    @PostMapping(value = "/skills", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Skill createSkill(@RequestBody Skill skill){
        log.debug("Create skill: {} {} {}", skill.getName(), skill.getDescription(), skill.getCategory());
        return skillRepository.save(skill);
    }

    @Transactional
    @PutMapping(value = "/skills", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Skill updateSkill(@RequestBody Skill skill){
        log.debug("Update skill: {}", skill.getId());
        return skillRepository.save(skill);
    }

    @Transactional
    @DeleteMapping(value = "/skills/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteSkill(@RequestBody Skill skill){
        log.debug("Delete skill: {}", skill.getId());
        skillRepository.delete(skill);
        return ResponseEntity.ok().build();
    }
}
