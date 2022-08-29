package com.example.WeddingPlannerApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Swagger Configuration for the Wedding Planner API REST:
 * http://localhost:9090/swagger-ui/
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Wedding Planner API REST",
                "API REST docs for the Wedding Planner App",
                "1.0",
                "http://www.google.com",
                new Contact(
                        "Juan Ignacio Campos Martí",
                        "https://github.com/N4choCM?tab=repositories",
                        "nachocamposmarti@gmail.com"
                ),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }
}