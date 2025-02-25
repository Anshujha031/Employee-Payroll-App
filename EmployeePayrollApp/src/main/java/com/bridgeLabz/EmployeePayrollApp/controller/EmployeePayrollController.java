package com.bridgeLabz.EmployeePayrollApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeePayrollController {

    @GetMapping
    public String getEmployee(){
        return "Returning Employee Payroll Data";
    }
}
