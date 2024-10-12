package com.sanjeet.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditImpl")
@OpenAPIDefinition(
		info = @Info(	title = "Loans Microservice API Documentation",
						version = "1.0",
						description = "Self Learning Loansmicroservice Rest API Documentation",
						contact = @Contact(name = "Sanjeet Kumar Nayak", email = "nayaksanjeetkumar.com", url = "http://sanjeet.com"),
						license = @License(name = "Apache License, Version 2.0",  url = "http://sanjeet.com")
		),
	externalDocs = @ExternalDocumentation(description = "Loans Microservice Documentation", url = "http://sanjeet.com"))
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
