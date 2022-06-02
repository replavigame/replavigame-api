package com.coach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket scrumAllyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Replavigame API")
                .description(
                        "Replavigame is a web application designed for create guies, session or review about a game")
                .contact(new Contact("Replavigame", "github.com", "replavigame@hotmail.com"))
                .build();
    }
}
