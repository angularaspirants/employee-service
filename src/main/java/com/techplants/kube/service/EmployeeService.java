package com.techplants.kube.service;

import com.techplants.kube.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(int employeeId);

    public String creatEmployee(Employee employee);

    public Employee updateEmployee(int employeeId, Employee employee);

    public String deleteEmployeeById(int employeeId);
}
