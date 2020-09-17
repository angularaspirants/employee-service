package com.techplants.kube.service;

import com.techplants.kube.entity.DoctorEntity;
import com.techplants.kube.exception.ResourceNotFoundException;
import com.techplants.kube.model.Doctor;
import com.techplants.kube.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.techplants.kube.utils.ResponseUtil.resourceUri;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService;

    @Override
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctorEntity -> {
            doctors.add(toModel(doctorEntity));
        });

        return ResponseEntity
                .ok()
                .location(resourceUri(doctors.size()))
                .body(doctors);
    }

    @Override
    public ResponseEntity<Doctor> getDoctorById(int doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctorEntity -> toModel(doctorEntity))
                .map(doctor -> {
                    return ResponseEntity.ok()
                            .location(resourceUri(doctor.getDoctorId()))
                            .body(doctor);
                }).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ResponseEntity<Doctor> createDoctor(Doctor doctor) {
        return Optional.of(toEntity(doctor))
                .map(doctorRepository::save)
                .map(doctorEntity -> {
                    return ResponseEntity
                            .created(resourceUri(doctorEntity.getDoctorId()))
                            .body(toModel(doctorEntity));
                }).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public ResponseEntity<Doctor> updateDoctor(int doctorId, Doctor doctor) {
        return doctorRepository.findById(doctorId)
                .map(doctorEntity -> toEntity(doctor))
                .map(doctorRepository::save)
                .map(doc -> ResponseEntity
                        .ok()
                        .location(resourceUri(doc.getDoctorId()))
                        .body(toModel(doc))
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "doctorId " + doctorId + " Not Found"
                        )
                );
    }

    @Override
    public ResponseEntity<?> deleteDoctor(int doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctorEntity -> {
                    doctorRepository.delete(doctorEntity);
                    return ResponseEntity
                            .ok()
                            .build();
                }).orElseThrow(() -> new ResourceNotFoundException(
                        "doctorId " + doctorId + " Not Found"
                ));

    }

    @Override
    public ResponseEntity<Doctor> getPatientsByDoctorId(int doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctorEntity -> {
                    Doctor doctor = toModel(doctorEntity);
                    doctor.setPatients(patientService.getPatientsByDoctor(doctorEntity));
                    return doctor;
                })
                .map(doctor -> {
                    return ResponseEntity.ok()
                            .location(resourceUri(doctor.getDoctorId()))
                            .body(doctor);
                }).orElseThrow(IllegalArgumentException::new);
    }

    private static Doctor toModel(DoctorEntity doctorEntity){
        return Doctor.builder()
                .doctorId(doctorEntity.getDoctorId())
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .age(doctorEntity.getAge())
                .experience(doctorEntity.getExperience())
                .insurance(doctorEntity.getInsurance())
                .rating(doctorEntity.getRating())
                .build();
    }

    private static DoctorEntity toEntity(Doctor doctorModel){
        return DoctorEntity.builder()
                .doctorId(doctorModel.getDoctorId())
                .firstName(doctorModel.getFirstName())
                .lastName(doctorModel.getLastName())
                .age(doctorModel.getAge())
                .experience(doctorModel.getExperience())
                .insurance(doctorModel.getInsurance())
                .rating(doctorModel.getRating())
                .build();
    }
}
