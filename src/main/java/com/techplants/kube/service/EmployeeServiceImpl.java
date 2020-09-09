package com.techplants.kube.service;

import com.techplants.kube.entity.EmployeeEntity;
import com.techplants.kube.model.Employee;
import com.techplants.kube.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        return employeeEntities.stream().map(entity -> toModel(entity)).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Optional<EmployeeEntity> entity = employeeRepository.findById(employeeId);
        return toModel(entity.get());
    }

    @Transactional
    @Override
    public String creatEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.save(toEntity(employee));
        return "employeCreated";
    }

    @Transactional
    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        Optional<EmployeeEntity> entityOptional = employeeRepository.findById(employeeId);
        EmployeeEntity entity = entityOptional.get();
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setAge(employee.getAge());


        return toModel(entity);
    }

    @Override
    public String deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
        return "employeeDeleted";
    }

    private static EmployeeEntity toEntity(Employee employee){
        return EmployeeEntity.builder()
                .employeeId(employee.getEmployeeId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .age(employee.getAge())
            .build();
}

    private static Employee toModel(EmployeeEntity employeeEntity){
        return Employee.builder()
                .employeeId(employeeEntity.getEmployeeId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .age(employeeEntity.getAge())
                .build();
    }

}
