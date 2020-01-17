package com.thuannluit.quanlynhansu.service;

import com.thuannluit.quanlynhansu.model.Employee;

import java.util.Optional;

public class EmployeeNotFoundException extends Exception  {
    public EmployeeNotFoundException(Integer id) {
     super(String.format("Employee is not found with id : '%s'", id));
    }
}
