package com.bridgeLabz.EmployeePayrollApp.controller;

import com.bridgeLabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeePayrollController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //GET all employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    //POST - add new employees
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO.getName(),employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    //PUT - update employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id , @RequestBody Employee updatedEmployeeDTO){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployeeDTO.getName());
                    employee.setSalary(updatedEmployeeDTO.getSalary());
                    return employeeRepository.save(employee);
                })
                .orElse(null);
    }
    // DELETE employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }
}
