package com.example.imsmanagemnt.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imsmanagemnt.employee.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
