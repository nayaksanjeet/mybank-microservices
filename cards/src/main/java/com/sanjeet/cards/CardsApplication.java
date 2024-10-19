package com.sanjeet.cards;

import com.sanjeet.cards.dto.CardsContactInfo;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditImpl")
@EnableConfigurationProperties(value = {CardsContactInfo.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice Rest API Documentation",
                version = "1.0.0",
                description = "Self Learning cards microservice Rest API Documentation",
                contact = @Contact(
                        name = "Sanjeet Kumar Nayak",
                        url = "https://github.com/sanjeetkumar",
                        email = "nayaksanjeetkumar@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "My self Cards Learning microservice Docs",
                url = "https://github.com/sanjeetkumar"
        )
)
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
