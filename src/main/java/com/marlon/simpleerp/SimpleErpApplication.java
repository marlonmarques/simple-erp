package com.marlon.simpleerp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SimpleERP API", version = "1.0", description = "API de Gestão Empresarial"))
public class SimpleErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleErpApplication.class, args);
	}

}
