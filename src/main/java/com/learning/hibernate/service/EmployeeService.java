package com.learning.hibernate.service;

import com.learning.hibernate.domain.Employee;
import com.learning.hibernate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Employee> getOneEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public Employee updateSalary(Employee emp, double salary) {
        emp.setSalary(salary);
        return employeeRepository.save(emp);
    }

    public void deleteEmployee(Employee emp) {
        employeeRepository.delete(emp);
    }
}
