package com.test.jpalearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpalearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpalearningApplication.class, args);
	}

}
