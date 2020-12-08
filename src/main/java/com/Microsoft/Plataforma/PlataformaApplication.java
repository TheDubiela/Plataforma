package com.Microsoft.Plataforma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"com.Microsoft.Plataforma.controller", "com.Microsoft.Plataforma.dao","com.Microsoft.Plataforma.entity","com.Microsoft.Plataforma.service"})
public class PlataformaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaApplication.class, args);
	}

}
