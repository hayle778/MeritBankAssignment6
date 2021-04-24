package com.meritamerica.assignment6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackageClasses = AccountHoldersRepository.class)
//@Configuration
//@EnableWebMvc
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
