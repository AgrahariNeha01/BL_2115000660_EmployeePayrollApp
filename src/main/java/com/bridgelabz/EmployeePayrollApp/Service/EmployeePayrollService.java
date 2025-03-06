package com.bridgelabz.EmployeePayrollApp.Service;

import com.bridgelabz.EmployeePayrollApp.Model.Employee;
import com.bridgelabz.EmployeePayrollApp.Repository.EmployeeRepository;
import com.bridgelabz.EmployeePayrollApp.DTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> empList = new ArrayList<>(); // Memory Storage

    public List<Employee> getAllEmployees() {
        List<Employee> dbEmployees = employeeRepository.findAll(); // DB Data
        dbEmployees.addAll(empList); // List ka Data Merge Karo
        return dbEmployees;
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        empList.add(employee); // List me Add Karo
        return employeeRepository.save(employee); // DB me Save Karo
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(int id, EmployeeDTO updatedEmployeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));

        existingEmployee.setName(updatedEmployeeDTO.getName());
        existingEmployee.setSalary(updatedEmployeeDTO.getSalary());

        // Memory List me bhi Update Karo
        for (Employee emp : empList) {
            if (emp.getId() == id) {
                emp.setName(updatedEmployeeDTO.getName());
                emp.setSalary(updatedEmployeeDTO.getSalary());
            }
        }

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);

        // Memory List se bhi Remove Karo
        empList.removeIf(emp -> emp.getId() == id);
    }
}
