package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {

        return employeeService.createEmployee(employee);

    }

    @GetMapping("/display")
    public List<Employee> displayEmployees() {

        return employeeService.displayEmployees();

    }

    @PutMapping("/raiseSalary")
    public Employee raiseSalary(@RequestParam String name,
                                @RequestParam double percentage) {

        return employeeService.raiseSalary(name, percentage);

    }

}