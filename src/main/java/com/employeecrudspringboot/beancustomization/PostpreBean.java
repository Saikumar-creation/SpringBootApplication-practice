package com.employeecrudspringboot.beancustomization;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PostpreBean {

	public PostpreBean() {
		System.out.println("constructor from PostpreBean");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct from PostpreBean this.hashCode() : " + this.hashCode());
		System.out.println("postConstruct from PostpreBean hashCode() : " + hashCode());
	}

	// @PreDestroy
	public void preDestroy() {
		System.out.println("preDestroy from PostpreBean");
	}
}
