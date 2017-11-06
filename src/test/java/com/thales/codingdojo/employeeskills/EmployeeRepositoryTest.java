package com.thales.codingdojo.employeeskills;

import com.thales.codingdojo.employeeskills.domain.Employee;
import com.thales.codingdojo.employeeskills.domain.Skill;
import com.thales.codingdojo.employeeskills.service.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repo;

    @Before
    public void init() throws ParseException {
        repo.save(new Employee(null,"Payneau", "Nicolas", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Payneau", "nicolas", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Payneau", "Nico", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Guillet", "Matthieu", localDateToDate(LocalDate.of(1985, Month.APRIL, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Dupont", "Julien", localDateToDate(LocalDate.of(1955, Month.AUGUST, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Dupont", "Nicolas", localDateToDate(LocalDate.of(1955, Month.AUGUST, 12)), new HashSet<Skill>()));

    }

    private Date localDateToDate(LocalDate ld) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(ld.toString());
    }

    @Test
    public void findByLastnameOrderByFirstnameAsc() throws Exception {
        List<Employee> result = repo.findByLastNameOrderByFirstNameAsc("Nicolas");
        Assert.assertEquals("Dupont",result.get(0).getFirstName());
    }


    @Test
    public void countByLastnameIgnoreCase() throws Exception {
        Assert.assertEquals(3,repo.countByLastNameIgnoreCase("Nicolas"));
    }
}
