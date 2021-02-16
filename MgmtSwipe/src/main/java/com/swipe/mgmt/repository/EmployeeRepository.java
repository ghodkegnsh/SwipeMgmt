package com.swipe.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipe.mgmt.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmpId(int empId);

}
