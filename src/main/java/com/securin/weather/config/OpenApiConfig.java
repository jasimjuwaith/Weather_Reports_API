package com.securin.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Weather Application API")
						.description("API docs for Weather Application")
						.version("1.0.0")
						.contact(new Contact()
								.name("Developer Support")
								.email("support@example.com")
								.url("https://example.com"))
						.license(new License()
								.name("Apache 2.0")
								.url("some url")))
				.externalDocs(new ExternalDocumentation()
						.description("Weather Application Docs")
						.url("some url"));
	}
}
