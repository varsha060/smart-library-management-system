package com.varsha.smartlibrary.config;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI smartLibraryAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Library Management System API")
                        .description("REST APIs for managing books, categories, members, borrowing, returns, fines, and JWT authentication.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Varsha H L")
                                .email("varsha@example.com")));
    }

}

