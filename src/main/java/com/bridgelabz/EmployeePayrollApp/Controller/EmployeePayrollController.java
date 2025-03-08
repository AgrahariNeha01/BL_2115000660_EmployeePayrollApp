package com.bridgelabz.EmployeePayrollApp.Controller;

import com.bridgelabz.EmployeePayrollApp.Model.Employee;
import com.bridgelabz.EmployeePayrollApp.Service.EmployeePayrollService;
import com.bridgelabz.EmployeePayrollApp.DTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDTO updatedEmployeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, updatedEmployeeDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

    @GetMapping("/logtest")
    public String logExample() {
        log.info("This is an INFO log message!");
        log.debug("This is a DEBUG log message!");
        log.error("This is an ERROR log message!");
        return "Logging enabled!";
    }
}
