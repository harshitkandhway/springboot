package com.learning.hibernate.service;

import com.learning.hibernate.domain.Employee;
import com.learning.hibernate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> listAll() {
    return employeeRepository.findAll();
    }
}
