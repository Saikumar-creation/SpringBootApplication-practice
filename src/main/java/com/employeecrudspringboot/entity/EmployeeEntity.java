package com.employeecrudspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 30)
	private String dept;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, length = 10)
	private String mobile;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false)
	private String status;
}
