package com.bridgelabz.EmployeePayrollApp.Service;

import com.bridgelabz.EmployeePayrollApp.Model.Employee;
import com.bridgelabz.EmployeePayrollApp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
