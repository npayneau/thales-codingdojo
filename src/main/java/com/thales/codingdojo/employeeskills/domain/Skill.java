package com.thales.codingdojo.employeeskills.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="skill_generator", sequenceName="skill_sequence"    )
    @GeneratedValue(generator = "skill_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SkillGroup category;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private Set<Employee> employees;
}
