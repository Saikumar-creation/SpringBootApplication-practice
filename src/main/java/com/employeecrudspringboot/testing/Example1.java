package com.employeecrudspringboot.testing;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "example1", value = "enabled", havingValue = "true", matchIfMissing = true)
public class Example1 {
	public Example1() {
		System.out.println("constructor from Example1");
	}
}
