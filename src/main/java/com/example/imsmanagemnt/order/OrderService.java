package com.example.imsmanagemnt.order;

import java.util.List;

import com.example.imsmanagemnt.employee.Employee;
import com.example.imsmanagemnt.employee.EmployeeDto;

public interface OrderService {

    Order save(OrderDto orderDto);

    List<Order> getAll();

}
