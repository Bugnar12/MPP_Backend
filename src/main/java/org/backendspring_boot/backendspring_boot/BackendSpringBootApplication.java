package org.backendspring_boot.backendspring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan("org.backendspring_boot.backendspring_boot.entity")
//@ComponentScan({"org.example.controller", "org.example.service", "org.example.repository", "org.example.config"})
public class BackendSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendSpringBootApplication.class, args);
	}
}