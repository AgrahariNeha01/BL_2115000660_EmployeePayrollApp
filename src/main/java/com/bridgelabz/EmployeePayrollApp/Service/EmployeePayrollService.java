package com.bridgelabz.EmployeePayrollApp.Service;

import com.bridgelabz.EmployeePayrollApp.Exception.EmployeeNotFoundException;
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

    public Employee getEmployeeById(int id) {  // ✅ Duplicate method hata diya
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found!"));
    }

    public Employee updateEmployee(int id, EmployeeDTO updatedEmployeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found!"));

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
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found!");
        }
        employeeRepository.deleteById(id);

        // Memory List se bhi Remove Karo
        empList.removeIf(emp -> emp.getId() == id);
    }
}
