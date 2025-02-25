package com.bridgeLabz.EmployeePayrollApp.service;


import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollService {


    private final List<Employee> employeeList = new ArrayList<>();
    private long employeeIdCounter = 1; // Counter for assigning unique IDs

    public List<Employee> getAllEmployees(){

        return employeeList;
    }

    public Optional<Employee> getEmployeeById(Long id){

        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public Employee createEmployee(Employee employee){

        employee.setId(employeeIdCounter++); // Assign a unique ID
        employeeList.add(employee);
        return employee;
    }

    public Optional<Employee> updateEmployee(Long id , Employee updatedEmployee){
        return getEmployeeById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
            return employee;
        });
    }

    public boolean deleteEmployee(Long id) {

        return employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}
