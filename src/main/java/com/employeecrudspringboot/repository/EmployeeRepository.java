package com.employeecrudspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeecrudspringboot.entity.EmployeeEntity;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

	@Query("SELECT e FROM EmployeeEntity e WHERE e.id = :id")
	EmployeeEntity findEmployeeById(@Param("id") Integer id);

	@Transactional
	@Modifying
	@Query("delete EmployeeEntity u  where u.id = :id")
	void deleteEmployeeById(Integer id);

}