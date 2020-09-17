package com.techplants.kube.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Doctor {

    private Integer doctorId;

    private String firstName;

    private String lastName;

    private int rating;

    private int age;

    private int experience;

    private String insurance;

    private List<Patient> patients;

}
