package com.techplants.kube.service;

import com.techplants.kube.model.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    public ResponseEntity<List<Patient>> getAllPatients();

    public ResponseEntity<Patient> getPatientById(int patientId);

    public ResponseEntity<Patient> createPatient(Patient patient);

    public ResponseEntity<Patient> updatePatient(int patientId, Patient patient);

    public ResponseEntity<?> deletePatientById(int patientId);
}
