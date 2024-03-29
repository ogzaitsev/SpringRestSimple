package com.olegzcv.rest.controller;

import com.olegzcv.rest.entity.Employee;
import com.olegzcv.rest.exception_handling.ExceptionData;
import com.olegzcv.rest.exception_handling.NoSuchEmployeeException;
import com.olegzcv.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRESTController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public List<Employee> test() {
        List<Employee> result = service.getAllEmployees();
        System.out.println(result);
        return result;
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee result = service.getEmployee(id);
        if(result == null) {
            throw new NoSuchEmployeeException("there is no employee with id "+ id);
        }
        return result;
    }

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return employee;
    }
    @PutMapping ("/")
    public Employee updateEmployee(@RequestBody Employee employee) {
        service.updateEmployee(employee);
        return employee;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        String response;
        if(service.deleteEmployee(id)) {
            response = "Employee ID=" + id + " was deleted";
        } else {
            throw new NoSuchEmployeeException("There is no employee with id=" + id);
        }
        return response;
    }
}
