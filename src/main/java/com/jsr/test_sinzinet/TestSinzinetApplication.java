package com.jsr.test_sinzinet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestSinzinetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSinzinetApplication.class, args);
	}

}
