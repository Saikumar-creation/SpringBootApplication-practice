package com.employeecrudspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootapplicationApplication  {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootapplicationApplication.class, args);
		// context.close(); //stop application explecetly after beans created
	}

	 

	@Bean
	public ModelMapper objModelMapper() {
		return new ModelMapper();
	}
}
