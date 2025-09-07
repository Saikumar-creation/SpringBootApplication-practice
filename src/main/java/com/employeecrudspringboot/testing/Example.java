package com.employeecrudspringboot.testing;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "example", value = "enabled", havingValue = "create", matchIfMissing = true)
public class Example {

	public Example() {
		System.out.println("constructor from Example");
	}

}
