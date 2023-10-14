package com.ProjetoEscola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI CustomApiConfig() {
		return new OpenAPI().info(infoAPI());
	}
	
	public Info infoAPI() {
		return new Info()
			.title("Restfull API")
			.description("Uma API para ser o projeto de PPWI4")
			.termsOfService("http://ppwi4.bri.ifsp.edu.br")
			.license(
				new License()
					.name("Restfull API")
					.url("http://ppwi4.bri.ifsp.edu.br")
			);
	}

}
