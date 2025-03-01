package com.bridgeLabz.EmployeePayrollApp.service;


import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeePayrollService {


    private final List<Employee> employeeList = new ArrayList<>();
    private long employeeIdCounter = 1; // Counter for assigning unique IDs

    public List<Employee> getAllEmployees(){

        log.info("Fetching all employee. Total count : {}",employeeList.size() );
        return employeeList;
    }

    public Optional<Employee> getEmployeeById(Long id){

        log.info("Fetching employee with ID: {}", id);
        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public Employee createEmployee(Employee employee){

        employee.setId(employeeIdCounter++); // Assign a unique ID
        employeeList.add(employee);
        log.info("Created new employee: {}", employee);
        return employee;
    }

    public Optional<Employee> updateEmployee(Long id , Employee updatedEmployee){
        return getEmployeeById(id).map(employee -> {
            log.info("Updating employee with ID: {}", id);
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
            log.info("Updated employee details: {}", employee);
            return employee;
        });
    }

    public boolean deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        boolean removed = employeeList.removeIf(employee -> employee.getId().equals(id));
        if (removed) {
            log.info("Employee with ID {} deleted successfully.", id);
        } else {
            log.error("Failed to delete employee with ID {} - Not Found.", id);
        }
        return removed;
    }
}
