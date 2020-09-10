package com.techplants.kube.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Patient {

    private Integer patientId;

    private String firstName;

    private String lastName;

    private int visits;

    private int age;

    private String insurance;

    private Doctor doctor;

}
