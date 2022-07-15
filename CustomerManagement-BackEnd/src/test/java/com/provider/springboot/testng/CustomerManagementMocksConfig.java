package com.provider.springboot.testng;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.provider.springboot.repository.SubscriberRepository;


@TestConfiguration
public class CustomerManagementMocksConfig {

	@Bean
	@Primary
	public SubscriberRepository themeRepository() {
		return Mockito.mock(SubscriberRepository.class);
	}
	
	
	
}
