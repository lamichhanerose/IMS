package com.example.imsmanagemnt.employee;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.imsmanagemnt.user.User;
import com.example.imsmanagemnt.user.UserRegistrationDto;

public interface EmployeeService {

    Employee save(EmployeeDto employeeDto);

    List<Employee> getAll();

}
