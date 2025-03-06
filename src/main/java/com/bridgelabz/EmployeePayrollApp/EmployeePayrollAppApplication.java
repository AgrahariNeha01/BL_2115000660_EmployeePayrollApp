package com.bridgelabz.EmployeePayrollApp;

import com.bridgelabz.EmployeePayrollApp.Repository.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeePayrollAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePayrollAppApplication.class, args);
		DBConnection.getConnection();
	}

}
