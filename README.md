# Project 03  - Employee Management System with Authentication

## Project Overview

This project demonstrates the implementation of an **Employee Management System integrated with User Authentication** using Spring Boot.

The objective of this assignment is to understand how secure authentication mechanisms can be combined with CRUD operations to develop a complete browser-based application.

The application allows users to:

* Register new accounts
* Login securely
* Recover forgotten passwords
* Manage employee information
* Update employee salaries
* Logout from the system

This project follows the MVC architecture and uses Thymeleaf for the frontend and H2 Database for data persistence.

---

# Architecture

```text
                 Browser

                    │

                    ▼

            Thymeleaf Templates

                    │

                    ▼

               Controller Layer

                    │

                    ▼

                Service Layer

                    │

                    ▼

              Repository Layer

                    │

                    ▼

                H2 Database


        ┌────────────────────┐
        │                    │
        ▼                    ▼

    APP_USER            EMPLOYEE
```

---

# Technologies Used

* Java 17
* Spring Boot 4.1.0
* Spring MVC
* Spring Data JPA
* Thymeleaf
* H2 Database
* Bootstrap 5
* BCrypt Password Encoder
* Maven
* STS / Eclipse
* Postman

---

# Project Structure

```text
EmployeeManagementSystem

│

├── controller

│      EmployeeController.java
│      PageController.java

│

├── entity

│      Employee.java
│      AppUser.java

│

├── repository

│      EmployeeRepository.java
│      UserRepository.java

│

├── service

│      EmployeeService.java
│      EmployeeServiceImpl.java

│

├── config

│      SecurityConfig.java

│

└── templates

       login.html
       register.html
       forgotPassword.html
       resetPassword.html
       index.html
       create.html
       display.html
       raiseSalary.html
       thankyou.html
```

---

# Authentication Module

The authentication module provides secure access to the Employee Management System.

Implemented features:

* Registration
* Login
* Logout
* Forgot Password
* Password Reset
* Session Validation
* BCrypt Password Encryption

---

## User Registration

Allows new users to create accounts.

### Endpoint

```text
/register
```

### Validation Rules

Username:

```text
4–15 Characters

Letters

Numbers

Underscore
```

Password:

```text
6–15 Characters

At least one alphabet

At least one digit
```

### Encryption

Passwords are stored using:

```text
BCrypt
```

instead of plain text.

---

## Login

Authenticates users using credentials stored inside the database.

### Endpoint

```text
/login
```

### Session Handling

Upon successful login:

```text
Session Created

↓

User Redirected

↓

Dashboard
```

---

## Forgot Password

Users can recover passwords using security questions.

Workflow:

```text
Forgot Password

↓

Enter Username

↓

Security Question

↓

Verify Answer

↓

Reset Password

↓

Password Encrypted

↓

Login Again
```

---

# Employee Module

The Employee module provides employee management operations.

Implemented features:

* Create Employee
* Display Employee
* Raise Salary
* Exit

---

## Employee Entity

Fields:

```text
id

name

age

designation

salary
```

---

## Validations

### Name

```text
Only alphabets

Maximum two spaces

No numbers

No special characters
```

---

### Age

```text
18 < age < 60
```

---

### Designation

Allowed values:

```text
PROGRAMMER

MANAGER

TESTER
```

---

## Salary Raise

Allowed Range:

```text
1% – 10%
```

---

# Database Tables

## EMPLOYEE

```text
id

name

age

designation

salary
```

---

## APP_USER

```text
id

username

password

securityQuestion

securityAnswer
```

---

# REST Endpoints

## Authentication APIs

### Register

```text
POST

/register
```

---

### Login

```text
POST

/login
```

---

### Forgot Password

```text
POST

/forgotPassword
```

---

### Reset Password

```text
POST

/resetPassword
```

---

## Employee APIs

### Create Employee

```text
POST

/create
```

---

### Display Employee

```text
GET

/display
```

---

### Raise Salary

```text
PUT

/raiseSalary
```

---

# Running the Project

## Step 1

Run:

```text
EmployeeManagementApplication.java
```

---

## Step 2

Open:

```text
http://localhost:2020
```

Expected page:

```text
Login Page
```

---

## Step 3

Create a new account.

Click:

```text
Create New Account
```

Fill:

```text
Username

Password

Security Question

Security Answer
```

---

## Step 4

Login using registered credentials.

Expected:

```text
Employee Dashboard
```

---

## Step 5

Create employees.

Expected:

```text
Employee saved successfully
```

---

## Step 6

Display employees.

Expected:

```text
Employee table displayed
```

---

## Step 7

Raise Salary.

Allowed:

```text
1%

to

10%
```

Expected:

```text
Updated salary stored
```

---

## Step 8

Logout.

Session destroyed.

Redirect:

```text
Login Page
```

---

# H2 Database

Open:

```text
http://localhost:2020/h2-console
```

Expected tables:

```text
EMPLOYEE

APP_USER
```

---

# Postman Testing

### Register

```text
POST

/register
```

---

### Login

```text
POST

/login
```

---

### Create Employee

```text
POST

/create
```

---

### Display Employee

```text
GET

/display
```

---

### Raise Salary

```text
PUT

/raiseSalary
```

---

# Security Workflow

```text
User Registration

↓

Password Encryption

↓

Stored in APP_USER

↓

Login

↓

Credential Validation

↓

BCrypt Match

↓

Session Created

↓

Dashboard Access
```

---

# Advantages

* Secure password storage
* Session management
* Browser-based interface
* Employee CRUD operations
* Password recovery mechanism
* MVC architecture implementation
* Beginner-friendly authentication workflow

---

# What I Learned

Through this project, I learned:

* MVC Architecture
* Thymeleaf Integration
* Spring Data JPA
* H2 Database
* Session Management
* BCrypt Password Encryption
* Authentication Workflow
* Password Recovery Mechanism
* CRUD Operations
* Postman API Testing
* Bootstrap UI Development

---

# Project 03 Summary

In this assignment, an Employee Management System was developed with integrated authentication features.

The application provides secure login functionality, user registration, password recovery, employee management operations, and session-based access control.

This project helped in understanding how authentication and CRUD functionalities can be combined to build a complete Spring Boot web application.

---

# Future Enhancements

This project can be extended by integrating:

* Spring Security
* JWT Authentication
* Role Based Access Control
* MySQL Database
* Docker
* Eureka Server
* API Gateway
* OpenFeign Client
* OAuth2
* Redis Session Management

---

# Author

**Md Parvezuddin**

B.Tech CSE

Java Full Stack Developer(Learning)
