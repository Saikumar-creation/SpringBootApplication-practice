package com.employeecrudspringboot.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public Model handleException(Exception ex, Model model) {
		System.out.println("Exception ex.getMessage() :" + ex.getMessage());
		model.addAttribute("errorMessage", ex.getMessage());
		model.addAttribute("status", 2);
		return model;
	}

	@ExceptionHandler(RuntimeException.class)
	public Model handleRuntimeException(RuntimeException ex, Model model) {
		System.out.println("RuntimeException ex.getMessage() :" + ex.getMessage());
		model.addAttribute("errorMessage", ex.getMessage());
		model.addAttribute("status", 2);
		return model;
	}
	@ExceptionHandler(NullPointerException.class)
	public Model nullPointerException(NullPointerException ex, Model model) {
		System.out.println("NullPointerException ex.getMessage() :" + ex.getMessage());
		model.addAttribute("errorMessage", ex.getMessage());
		model.addAttribute("status", 2);
		return model;
	}
}