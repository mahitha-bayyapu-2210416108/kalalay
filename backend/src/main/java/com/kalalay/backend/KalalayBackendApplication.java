package com.kalalay.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.kalalay.backend")
@EnableJpaRepositories(basePackages = "com.kalalay.backend.repository")
@EntityScan(basePackages = "com.kalalay.backend.model")
public class KalalayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalalayBackendApplication.class, args);
	}

}
