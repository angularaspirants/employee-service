package com.techplants.kube.repository;

import com.techplants.kube.entity.DoctorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Integer> {

}
