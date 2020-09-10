package com.techplants.kube.service;

import com.techplants.kube.entity.DoctorEntity;
import com.techplants.kube.entity.PatientEntity;
import com.techplants.kube.exception.ResourceNotFoundException;
import com.techplants.kube.model.Doctor;
import com.techplants.kube.model.Patient;
import com.techplants.kube.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.techplants.kube.utils.ResponseUtil.resourceUri;

public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patientEntity -> {
            patients.add(toModel(patientEntity));
        });

        return ResponseEntity
                .ok()
                .location(resourceUri(patients.size()))
                .body(patients);
    }

    @Override
    public ResponseEntity<Patient> getPatientById(int patientId) {
        return patientRepository.findById(patientId)
                .map(patientEntity -> toModel(patientEntity))
                .map(patient -> {
                    return ResponseEntity.ok()
                            .location(resourceUri(patient.getPatientId()))
                            .body(patient);
                }).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ResponseEntity<Patient> createPatient(Patient patient) {
        return Optional.of(toEntity(patient, null))
                .map(patientRepository::save)
                .map(patientEntity -> {
                    return ResponseEntity
                            .created(resourceUri(patientEntity.getPatientId()))
                            .body(toModel(patientEntity));
                }).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(int patientId, Patient patient) {
        return patientRepository.findById(patientId)
                .map(patientEntity -> toEntity(patient, null))
                .map(patientRepository::save)
                .map(pat -> ResponseEntity
                        .ok()
                        .location(resourceUri(pat.getPatientId()))
                        .body(toModel(pat))
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "doctorId " + patientId + " Not Found"
                        )
                );
    }

    @Override
    public ResponseEntity<?> deletePatientById(int patientId) {
        return null;
    }

    private static Patient toModel(PatientEntity patientEntity){
        return Patient.builder()
                .patientId(patientEntity.getPatientId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .age(patientEntity.getAge())
                .visits(patientEntity.getVisits())
                .insurance(patientEntity.getInsurance())
                .doctor(toDoctorModel(patientEntity.getDoctor()))
                .build();
    }

    private static PatientEntity toEntity(Patient patientModel, DoctorEntity doctorEntity){
        return PatientEntity.builder()
                .patientId(patientModel.getPatientId())
                .firstName(patientModel.getFirstName())
                .lastName(patientModel.getLastName())
                .age(patientModel.getAge())
                .visits(patientModel.getVisits())
                .insurance(patientModel.getInsurance())
                .doctor(doctorEntity)
                .build();
    }

    private static Doctor toDoctorModel(DoctorEntity doctorEntity){
        return Doctor.builder()
                .doctorId(doctorEntity.getDoctorId())
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .age(doctorEntity.getAge())
                .experience(doctorEntity.getExperience())
                .available(doctorEntity.getAvailable())
                .rating(doctorEntity.getRating())
                .build();
    }
}