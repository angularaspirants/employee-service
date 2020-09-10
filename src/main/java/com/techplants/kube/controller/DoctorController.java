package com.techplants.kube.controller;

import com.techplants.kube.model.Doctor;
import com.techplants.kube.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") int doctorId){
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId")int doctorId, @RequestBody Doctor doctor){
        return doctorService.updateDoctor(doctorId, doctor);
    }

    @DeleteMapping("/doctor/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("doctorId") int doctorId){
        return doctorService.deleteDoctor(doctorId);
    }
}
