package com.employeecrudspringboot.service;

import com.employeecrudspringboot.dto.DashboardDto;
import com.employeecrudspringboot.dto.EmployeeDto;

public interface IEmployeeService {

	void saveEmployee(EmployeeDto objEmployeeDto);

	DashboardDto findall();

	EmployeeDto getEmployeeById(Integer id);

	void deleteEmployeeById(Integer id);

}
