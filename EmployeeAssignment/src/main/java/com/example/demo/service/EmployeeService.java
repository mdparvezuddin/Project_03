package com.example.demo.service;
import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> displayEmployees();

    Employee raiseSalary(String name, double percentage);

}