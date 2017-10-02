package com.thales.codingdojo.employeeskills;

import static  org.hamcrest.CoreMatchers.*;

import com.thales.codingdojo.employeeskills.domain.Employee;
import com.thales.codingdojo.employeeskills.domain.Skill;
import com.thales.codingdojo.employeeskills.service.EmployeeRepository;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmployeeControllerTest {
    private MockMvc mockWebApp;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeRepository repo;

    @Before
    public void init() throws ParseException {
        this.mockWebApp = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        repo.save(new Employee(null,"Payneau", "Nicolas", localDateToDate(LocalDate.of(1985, Month.DECEMBER, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Guillet", "Matthieu", localDateToDate(LocalDate.of(1985, Month.APRIL, 12)), new HashSet<Skill>()));
        repo.save(new Employee(null,"Florian", "Dupond", localDateToDate(LocalDate.of(1955, Month.AUGUST, 12)), new HashSet<Skill>()));
    }

    private Date localDateToDate(LocalDate ld) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(ld.toString());
    }

    @Test
    public void contextLoads() throws Exception {
        mockWebApp.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].firstName", is("Payneau")));
    }

}
