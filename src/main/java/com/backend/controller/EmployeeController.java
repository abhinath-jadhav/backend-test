package com.backend.controller;

import com.backend.models.Employee;
import com.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@RestController
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExecutorService executorService;

    @GetMapping("/api/v1/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id){


       ;

        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/api/v1/employee")
    public ResponseEntity<?> getEmployee(){
        long l = System.currentTimeMillis();
        List<Employee>  employee = employeeService.getAllEmployee();
        System.out.println( "Time Taken -> " + (System.currentTimeMillis() - l));
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/api/v1/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp){
        employeeService.addEmployee(emp);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }

    @PutMapping("/api/v1/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee emp){
        System.out.println("update");
        employeeService.updateEmployee(emp);
        return new ResponseEntity<>(HttpStatus.valueOf(200));

    }

}
