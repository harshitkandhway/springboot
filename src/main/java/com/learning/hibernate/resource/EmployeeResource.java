package com.learning.hibernate.resource;

import com.learning.hibernate.domain.Employee;
import com.learning.hibernate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee1(@RequestBody Employee employee) {
        Employee added = employeeService.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }

//    @PostMapping
//    public ResponseEntity<Employee> addEmployee2(@RequestParam String name){
//        Employee employee = new Employee(name,"Manager",6000000.00);
//        Employee added = employeeService.add(employee);
//        return ResponseEntity.status(HttpStatus.CREATED).body(added);
//    }
//
//    @PostMapping("/{name}")
//    public ResponseEntity<Employee> addEmployeeWithName(@PathVariable(name = "name") String name){
//        Employee employee = new Employee(name,"Manager",6000000.00);
//        Employee added = employeeService.add(employee);
//        return ResponseEntity.status(HttpStatus.CREATED).body(added);
//    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.listAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id") int id) {
        Optional<Employee> found = employeeService.getOneEmployee(id);
        if (found.isPresent())
            return ResponseEntity.status(HttpStatus.FOUND).body(found.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateSalary(@PathVariable(name = "id") int id,@RequestParam double salary) {
        Optional<Employee> found = employeeService.getOneEmployee(id);
        if (found.isPresent()) {
            Employee employee = employeeService.updateSalary(found.get(), salary);
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(name = "id") int id) {
        Optional<Employee> employee = employeeService.getOneEmployee(id);
        if (employee.isPresent()) {
            employeeService.deleteEmployee(employee.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
