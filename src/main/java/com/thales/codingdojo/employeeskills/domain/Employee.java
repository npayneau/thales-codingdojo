package com.thales.codingdojo.employeeskills.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("skills")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="employee_generator", sequenceName="employee_sequence", initialValue = 10)
    @GeneratedValue(generator = "employee_generator")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="GMT")
    private Date birthDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "id_employee", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_skill", referencedColumnName = "id"))
    private Set<Skill> skills;
}
