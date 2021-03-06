package com.myjava.bootstrap.simpleapplication.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.myjava.bootstrap.simpleapplication.springboot.persistance.repo")
@EntityScan("com.myjava.bootstrap.simpleapplication.springboot.persistance.model")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
