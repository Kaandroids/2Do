package com.example._Do.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * <p>
 * This class configures the metadata (title, version, contact) displayed in the Swagger UI
 * and defines the Security Scheme (JWT Bearer Token) to allow testing secured endpoints
 * directly from the browser via the "Authorize" button.
 * </p>
 */
@Configuration
public class OpenApiConfig {

    // Properties injected from application.yml for easier management
    @Value("${application.title}")
    private String title;

    @Value("${application.version}")
    private String version;

    @Value("${application.description}")
    private String description;

    @Value("${application.contact.name}")
    private String contactName;

    @Value("${application.contact.email}")
    private String contactEmail;

    @Value("${application.contact.url}")
    private String contactUrl;

    /**
     * Creates the custom OpenAPI bean with General Info and Security Configuration.
     *
     * @return The configured OpenAPI object.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                        .contact(new Contact().name(contactName).email(contactEmail).url(contactUrl))
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("Enter your JWT Token here")
                )
        );
    }


}
