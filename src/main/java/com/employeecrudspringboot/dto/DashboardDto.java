package com.employeecrudspringboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashboardDto {
	private Long totalEmployees;
	private Long totalActiveEmployees;
	private Long totalInactiveEmployees;
	private List<EmployeeDto> employeeDto;
}
