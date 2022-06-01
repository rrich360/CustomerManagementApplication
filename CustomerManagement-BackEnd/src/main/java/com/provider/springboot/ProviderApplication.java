package com.provider.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.provider.springboot.repository")
@EntityScan("com.provider.springboot.entity")
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

}
