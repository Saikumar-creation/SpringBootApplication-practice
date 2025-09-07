package com.employeecrudspringboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {
	private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    @NotBlank(message = "Department is required")
    @Size(min = 2, max = 30, message = "Department must be 2-30 characters")
    private String dept;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobile;

    @NotBlank(message = "Age is required")
    @Pattern(regexp = "^[1-9][0-9]?$|^100$", message = "Age must be a number between 1 and 100")
    private String age;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "Active|Inactive", message = "Status must be either Active or Inactive")
    private String status;

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", dept=" + dept + ", email=" + email + ", mobile=" + mobile
				+ ", age=" + age + ", status=" + status + "]";
	}

}
