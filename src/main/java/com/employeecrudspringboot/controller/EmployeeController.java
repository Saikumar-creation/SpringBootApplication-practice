package com.employeecrudspringboot.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeecrudspringboot.beancustomization.PostpreBean;
import com.employeecrudspringboot.dto.EmployeeDto;
import com.employeecrudspringboot.service.IEmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@Scope("prototype")
//@Scope("request")
@RestController
@RequestMapping("employee")
public class EmployeeController {
	private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());

	@Autowired
	@Qualifier("EmployeeService")
	IEmployeeService iEmployeeService;

	@Autowired
	PostpreBean postpreBean;

	public EmployeeController() {
		logger.info("constructor from EmployeeController");
	}

	@Operation(summary = "Get Employee by ID", description = "Fetch an employee from database by their ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the employee"),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@GetMapping("/getEmployee/{id}")
	public EmployeeDto getEmployee(@PathVariable Integer id) {
		logger.info("getEmploye/{id} : "+id);
		return iEmployeeService.getEmployeeById(id);
	}
}
