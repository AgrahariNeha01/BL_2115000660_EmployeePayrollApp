package com.bridgelabz.EmployeePayrollApp.Repository;

import com.bridgelabz.EmployeePayrollApp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);

}
