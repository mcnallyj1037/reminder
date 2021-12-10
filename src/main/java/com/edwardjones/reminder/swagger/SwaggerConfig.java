package com.edwardjones.reminder.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    /**
     * @return API documentation
     */
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
            .group("sticky-notes-rest")
            .pathsToMatch("/stickynote/**")
            .build();
    }


    /**
     * @return API information.
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(
                new Info().title("Sticky Notes API")
//                    .contact(
//                        new Contact()
//                            .name("IS Financial Assessment - Client Discovery & Goals - Team 4 - Localhosts")
//                            .url("https://github.edwardjones.com/ej/Goals_and_Discovery_General_Docs/wiki/Client-Goals-&-Discovery-Requests-Documentation")
//                            .email("Tech-DiscGoals-Localhosts@edwardjones.com")
//                    )
                    .description("Service API for Sticky Notes")
                    .version("1.0.0")
            )
//            .externalDocs(
//                new ExternalDocumentation()
//                    .description("GitHub Repo")
//                    .url("https://github.edwardjones.com/ej/codefest-sticky-notes-rest")
//            )
            ;
    }

}
