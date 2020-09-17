package com.techplants.kube.repository;

import com.techplants.kube.entity.DoctorEntity;
import com.techplants.kube.entity.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, Integer> {

    List<PatientEntity> getPatientEntitiesByDoctor(@Param("doctorEntity") DoctorEntity doctorEntity);

}
