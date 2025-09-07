package com.employeecrudspringboot.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Profile("prod")
public class CheckingConditionalOnProperty {
	@Autowired(required = false)
	Example example;
	@Autowired(required = false)
	Example1 example1;

	@PostConstruct
	private void postConstruct() {
		System.out.println("postConstruct from CheckingConditionalOnProperty");
		System.out.println("constructor from Example :"+example);
		System.out.println("constructor from Example :"+example1);
	}

}
