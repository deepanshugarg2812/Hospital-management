package com.patient.management.PatientManagement.dao;

import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import com.patient.management.PatientManagement.dto.response.PatientDto;

import java.util.List;

/**
 * Dao layer for patient
 */
public interface PatientDao {
    void addPatient(PatientAddDto patientAddDto);
    List<PatientDto> fetchPatient(int page, int size);
    void dischargePatient(Long patientId);
}
