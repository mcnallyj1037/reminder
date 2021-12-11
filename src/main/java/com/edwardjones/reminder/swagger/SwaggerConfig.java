package com.edwardjones.reminder.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    /**
     * Grouped Open API config/
     * @return API documentation
     */
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
            .group("reminder-rest")
            .pathsToMatch("/stickynote/**")
            .build();
    }


    /**
     * Open API Info Config Bean
     * @return API information.
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info().title("Sticky Notes API")
                    .contact(new Contact()
                            .name("Wildcards Team,")
                            .url("https://github.com/mcnallyj1037/reminder/tree/master")
                            .email("jmcnally@mcnallysoftware.com")
                    )
                    .description("Service API for Sticky Notes")
                    .version("1.0.0")
            ).externalDocs(
                new ExternalDocumentation()
                    .description("GitHub Repo")
                    .url("https://github.com/mcnallyj1037/reminder/wiki")
            );
    }

}
