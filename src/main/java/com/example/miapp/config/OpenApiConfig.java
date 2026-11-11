package com.example.miapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hotelApiDocumentation() {

        return new OpenAPI()
                .info(new Info()
                        .title("Hotel API")
                        .description("API REST para la gestión de usuarios, hoteles, habitaciones y reservas.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Daniel Betancourt Romero")
                                .email("betancourtromerodaniel@gmail.com")));
    }
}
