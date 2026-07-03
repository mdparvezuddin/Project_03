package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Pattern(
        regexp = "^[A-Za-z]+(?: [A-Za-z]+){0,2}$",
        message = "Name should contain only alphabets and maximum 2 spaces."
    )
    private String name;

    @Min(value = 19, message = "Age must be greater than 18")
    @Max(value = 59, message = "Age must be less than 60")
    private int age;

    @NotBlank(message = "Designation is required")
    @Pattern(
        regexp = "PROGRAMMER|MANAGER|TESTER",
        message = "Designation must be PROGRAMMER, MANAGER or TESTER"
    )
    private String designation;

    @Min(value = 1, message = "Salary must be greater than 0")
    private double salary;

    // Default Constructor
    public Employee() {
    }

    // Parameterized Constructor
    public Employee(int id, String name, int age, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}