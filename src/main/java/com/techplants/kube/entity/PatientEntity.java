package com.techplants.kube.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
@Builder(toBuilder = true)
public class PatientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="patient_id")
    private Integer patientId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="visits")
    private int visits;

    @Column(name="age")
    private int age;

    @Column(name="insurance")
    private String insurance;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "doctor_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_doctor_id"
            )
    )
    private DoctorEntity doctor;

}
