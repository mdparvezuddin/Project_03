package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.Employee;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String securityQuestion,
                           @RequestParam String securityAnswer,
                           Model model) {

        if (!username.matches("^[a-zA-Z0-9_]{4,15}$")) {
            model.addAttribute("error",
                    "Username must be 4-15 characters and contain only letters, numbers or underscore.");
            return "register";
        }

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d).{6,15}$")) {
            model.addAttribute("error",
                    "Password must contain letters and numbers and be 6-15 characters long.");
            return "register";
        }

        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer.toLowerCase());

        userRepository.save(user);

        model.addAttribute("success", "Account created successfully. Please login.");
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        if (!username.matches("^[a-zA-Z0-9_]{4,15}$")) {
            model.addAttribute("error",
                    "Username must be 4-15 characters and contain only letters, numbers or underscore.");
            return "login";
        }

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d).{6,15}$")) {
            model.addAttribute("error",
                    "Password should contain letters and numbers and be 6-15 characters long.");
            return "login";
        }

        AppUser user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", username);
            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        return "index";
    }

    @GetMapping("/createPage")
    public String createPage(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("employee", new Employee());
        return "create";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "create";
        }

        employeeService.createEmployee(employee);
        return "redirect:/displayPage";
    }

    @GetMapping("/displayPage")
    public String displayPage(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("employees", employeeService.displayEmployees());
        return "display";
    }

    @GetMapping("/raiseSalaryPage")
    public String raiseSalaryPage(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        List<Employee> employees = employeeService.displayEmployees();
        model.addAttribute("employees", employees);

        return "raiseSalary";
    }

    @PostMapping("/updateSalary")
    public String updateSalary(@RequestParam String name,
                               @RequestParam double percentage,
                               Model model) {

        if (percentage < 1 || percentage > 10) {
            model.addAttribute("employees", employeeService.displayEmployees());
            model.addAttribute("error", "Salary percentage must be between 1 and 10.");
            return "raiseSalary";
        }

        employeeService.raiseSalary(name, percentage);
        return "redirect:/displayPage";
    }

    @GetMapping("/exit")
    public String exit() {
        return "thankyou";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/forgotPasswordPage")
    public String forgotPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam String username,
                                 Model model) {

        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("error", "Username not found.");
            return "forgotPassword";
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("question", user.getSecurityQuestion());

        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String username,
                                @RequestParam String securityAnswer,
                                @RequestParam String newPassword,
                                Model model) {

        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            model.addAttribute("error", "Invalid user.");
            return "forgotPassword";
        }

        if (!user.getSecurityAnswer().equals(securityAnswer.toLowerCase())) {
            model.addAttribute("username", username);
            model.addAttribute("question", user.getSecurityQuestion());
            model.addAttribute("error", "Incorrect security answer.");
            return "resetPassword";
        }

        if (!newPassword.matches("^(?=.*[A-Za-z])(?=.*\\d).{6,15}$")) {
            model.addAttribute("username", username);
            model.addAttribute("question", user.getSecurityQuestion());
            model.addAttribute("error", "Password must contain letters and numbers and be 6-15 characters long.");
            return "resetPassword";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        model.addAttribute("success", "Password reset successfully. Please login.");
        return "login";
    }
}