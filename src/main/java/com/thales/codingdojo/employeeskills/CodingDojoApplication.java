package com.thales.codingdojo.employeeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude= RepositoryRestMvcAutoConfiguration.class)
public class CodingDojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingDojoApplication.class, args);
	}
}
