package com.techplants.kube.controller;

import com.techplants.kube.model.Employee;
import com.techplants.kube.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/employee")
    public String createEmployee(@RequestBody Employee employee){
        return employeeService.creatEmployee(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId, @RequestBody Employee employee){
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }
}
