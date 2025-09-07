package com.employeecrudspringboot.controller;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employeecrudspringboot.beancustomization.PostpreBean;
import com.employeecrudspringboot.dto.ErrorResponse;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

//@Scope("prototype")

@Controller
@Slf4j
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	PostpreBean postpreBean;

	@Value("${spring.security.user.name}")
	private String name;

	@Value("${spring.security.user.password}")
	private String pwd;

	public LoginController() {
		logger.info("constructor from LoginController");
	}

	@PostConstruct
	private void init() {
		log.info("@PostConstruct from LoginController hashcode() for postpreBean : " + postpreBean.hashCode());
		logger.info("@PostConstruct from LoginController hashcode() for postpreBean : " + postpreBean.hashCode());
	}

	@GetMapping({ "/", "/sba", "/login" })
	public String home() {
		log.info("Login get method is calling");
		return "login";
	}

	@PostMapping("/perform_login")
	public String loginProcess(@RequestParam String username, @RequestParam String password,
			RedirectAttributes redirectAttributes) {
		logger.info("name :" + name + " and  pwd " + pwd);
		logger.info("username :" + username + " and password " + password);
		if (StringUtils.isBlank(name) || StringUtils.isBlank(pwd)) {
			throw new NullPointerException("User name or password is not registred in our data");
		}
		if (name.equals(username) && pwd.equals(password)) {
			redirectAttributes.addFlashAttribute("successMessage", username + " login successfully.");
			return "redirect:/dashboard";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Invalid usename and password.");
			return "redirect:/login";
		}
	}

	// @ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "Something went wrong");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}