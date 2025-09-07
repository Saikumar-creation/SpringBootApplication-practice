package com.employeecrudspringboot.aop;

import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class aspect {
	public void log() {
		log.info("Aspect class");
	}
}
