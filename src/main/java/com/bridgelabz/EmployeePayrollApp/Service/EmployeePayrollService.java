package com.bridgelabz.EmployeePayrollApp.Service;

import com.bridgelabz.EmployeePayrollApp.Model.Employee;
import com.bridgelabz.EmployeePayrollApp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return employeeRepository.findById(id).orElse(null);  // ✅ Fix for your error
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

}
