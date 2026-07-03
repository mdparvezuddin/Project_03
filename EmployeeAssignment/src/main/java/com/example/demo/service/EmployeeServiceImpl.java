package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> displayEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee raiseSalary(String name, double percentage) {

        Employee employee = employeeRepository.findByName(name);

        if (employee != null) {

            double newSalary = employee.getSalary()
                    + (employee.getSalary() * percentage / 100);

            employee.setSalary(newSalary);

            return employeeRepository.save(employee);
        }

        return null;
    }

}