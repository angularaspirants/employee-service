package com.techplants.kube.controller;

import com.techplants.kube.model.Patient;
import com.techplants.kube.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") int patientId){
        return patientService.getPatientById(patientId);
    }

    @PostMapping("/patient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

    @PutMapping("/patient/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("patientId")int patientId, @RequestBody Patient patient){
        return patientService.updatePatient(patientId, patient);
    }

    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable("patientId") int patientId){
        return patientService.deletePatientById(patientId);
    }
}
