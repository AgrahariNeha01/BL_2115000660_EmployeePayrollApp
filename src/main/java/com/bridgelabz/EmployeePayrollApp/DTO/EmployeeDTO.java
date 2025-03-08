package com.bridgelabz.EmployeePayrollApp.DTO;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name should start with a capital letter and have at least 3 characters")
    private String name;

    @Min(value = 10000, message = "Salary should be at least 10000")
    private double salary;
}
