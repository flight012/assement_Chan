package com.assmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.assmt.dao")
public class AssementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssementApplication.class, args);
	}

}
