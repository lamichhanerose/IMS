package com.example.imsmanagemnt.employee;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.imsmanagemnt.user.User;
import com.example.imsmanagemnt.user.UserRegistrationDto;
import com.example.imsmanagemnt.user.UserRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
}
