package com.bridgeLabz.EmployeePayrollApp.controller;

import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.repository.EmployeeRepository;
import com.bridgeLabz.EmployeePayrollApp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;




import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    //GET all employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeePayrollService.getAllEmployees();
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeePayrollService.getEmployeeById(id).orElse(null);
    }

    //POST - add new employees
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeePayrollService.createEmployee(employee);
    }

    //PUT - update employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeePayrollService.updateEmployee(id, updatedEmployee).orElse(null);
    }

    // DELETE employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
       boolean isDeleted =  employeePayrollService.deleteEmployee(id);
        return isDeleted ? "Employee deleted successfully" : "Employee not found";
    }
}
