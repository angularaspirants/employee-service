package com.techplants.kube.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctor")
@Builder(toBuilder = true)
@SequenceGenerator(name="doctorSeq", initialValue=10101, allocationSize=1)
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="doctorSeq")
    @Column(name="doctor_id")
    private Integer doctorId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="rating")
    private int rating;

    @Column(name="age")
    private int age;

    @Column(name="experience")
    private int experience;

    @Column(name="insurance")
    private String insurance;

}
