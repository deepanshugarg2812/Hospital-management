package com.patient.management.PatientManagement.repository;

import com.patient.management.PatientManagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE PATIENT_ENTITY SET IS_DISCHARGED=?1 WHERE ID=?2", nativeQuery = true)
    int updatePatientActiveStatus(boolean isActive, Long id);
}
