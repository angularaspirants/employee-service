package com.techplants.kube.service;

import com.techplants.kube.model.Doctor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {

    public ResponseEntity<List<Doctor>> getAllDoctors();

    public ResponseEntity<Doctor> getDoctorById(int doctorId);

    public ResponseEntity<Doctor> createDoctor(Doctor doctor);

    public ResponseEntity<Doctor> updateDoctor(int doctorId, Doctor doctor);

    public ResponseEntity<?> deleteDoctor(int doctorId);

    public ResponseEntity<Doctor> getPatientsByDoctorId(int doctorId);
}
