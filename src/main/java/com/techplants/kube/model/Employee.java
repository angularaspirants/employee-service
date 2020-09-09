package com.techplants.kube.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder()
public class Employee {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private int age;

}
