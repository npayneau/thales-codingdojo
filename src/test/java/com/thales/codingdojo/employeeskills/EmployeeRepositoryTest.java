package com.thales.codingdojo.employeeskills;

import com.thales.codingdojo.employeeskills.domain.Employee;
import com.thales.codingdojo.employeeskills.domain.Skill;
import com.thales.codingdojo.employeeskills.service.EmployeeRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repo;

    @Before
    public void init() throws ParseException {
        repo.save(new Employee(null, "Nicolas","Payneau", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null, "nicolas","payneau", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null, "Nico","Payneau", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null, "Mathieu","Guillet", localDateToDate(LocalDate.of(1985, Month.APRIL, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null, "Julien","Dupont", localDateToDate(LocalDate.of(1955, Month.AUGUST, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null, "Nicolas","Dupont", localDateToDate(LocalDate.of(1955, Month.AUGUST, 12)), new HashSet<Skill>()));
    }
    

    @After
    public void tearDown() throws ParseException {
        repo.deleteAll();
    }

    private Date localDateToDate(LocalDate ld) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(ld.toString());
    }

    @Test
    public void findByLastname() throws Exception {
        List<Employee> result = repo.findByLastName("Dupont");
        Assert.assertEquals(2,result.size());
        Assert.assertEquals("Dupont",result.get(0).getLastName());
        Assert.assertEquals("Dupont",result.get(1).getLastName());
    }

    @Test
    public void findByLastnameOrderByFirstnameAsc() throws Exception {
        List<Employee> result = repo.findByLastNameOrderByFirstNameAsc("Dupont");
        Assert.assertEquals("Dupont",result.get(0).getLastName());
        Assert.assertEquals("Julien",result.get(0).getFirstName());
    }

    @Test
    public void countByLastnameIgnoreCase() throws Exception {
        Assert.assertEquals(3,repo.countByLastNameIgnoreCase("payneau"));
    }

    @Test
    public void findFirstByOrderByLastNameAsc() throws Exception {
        Employee employee = repo.findFirstByOrderByLastNameAsc();
		Assert.assertEquals("Julien", employee.getFirstName());
		Assert.assertEquals("Dupont", employee.getLastName());
    }


    @Test
    public void findTop10ByLastName() throws Exception {
    	List<Employee> result = repo.findTop10ByLastName("Guillet");
    	Assert.assertEquals(1,result.size());
        Assert.assertEquals("Mathieu", result.get(0).getFirstName());
		Assert.assertEquals("Guillet", result.get(0).getLastName());
    }

    @Test
    @Transactional
    public void findByLastNameAndFirstNameAllIgnoreCase() throws Exception {
    	final Sort sort = new Sort("lastName");
        List<Employee> result = repo.findByLastNameAndFirstNameAllIgnoreCase("Payneau", "Nicolas", sort);
		Assert.assertEquals(2,result.size());
        Assert.assertEquals("Payneau",result.get(0).getLastName());
        Assert.assertEquals("Nicolas",result.get(0).getFirstName());
        Assert.assertEquals("payneau",result.get(1).getLastName());
        Assert.assertEquals("nicolas",result.get(1).getFirstName());
    }
}
