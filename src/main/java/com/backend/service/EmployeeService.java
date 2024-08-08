package com.backend.service;

import com.backend.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@Slf4j
public class EmployeeService {


    @Autowired
    private WebClient webClient;


    public Employee getEmployee(String id) {

            System.out.println("TEST");
        try {
            Thread.sleep(5000);
            return new Employee(id, UUID.randomUUID().toString(), "Dev", "IT");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public void addEmployee(Employee emp) {
       // employeeRepo.save(emp);
    }

    public void updateEmployee(Employee emp) {
        log.info(" data {}",emp);
       // employeeRepo.save(emp);
    }

    public void deleteEmployee(String id) {
      //  employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployee() {
     //   return employeeRepo.findAll();

        Mono<Employee> employeeMono1 = webClient.get()
                .uri("/employee/" + 1)
                .retrieve().bodyToMono(Employee.class);
        System.out.println("call - 1");
        Mono<Employee> employeeMono2 = webClient.get()
                .uri("/employee/" + 2)
                .retrieve().bodyToMono(Employee.class);
        System.out.println("call - 2");
        Mono<Employee> employeeMono3 = webClient.get()
                .uri("/employee/" + 3)
                .retrieve().bodyToMono(Employee.class);
        System.out.println("call - 3");

        Mono<Map<String, Employee>> map1 = Mono.zip(employeeMono1, employeeMono2, employeeMono3).map(tuple -> {
            Map<String, Employee> map = new HashMap<>();
            map.put(tuple.getT1().getId(), tuple.getT1());
            map.put(tuple.getT2().getId(), tuple.getT2());
            map.put(tuple.getT3().getId(), tuple.getT3());
            return map;
        });

        System.out.println("Process completed");

        return new ArrayList<>(map1.block().values());
    }
}
