package com.axa.softwareacademy.p7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PoseidonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoseidonApplication.class, args);
	}

}
