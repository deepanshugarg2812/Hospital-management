package com.patient.management.PatientManagement.service;

import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import com.patient.management.PatientManagement.dto.response.PatientDto;

import java.util.List;

/**
 * Class is used to handle the patient request
 */
public interface PatientService {
    /**
     * Method is used to add patient in system
     * @param patientAddDto
     * @return
     */
    void addPatient(PatientAddDto patientAddDto);

    /**
     * Method is used to fetch
     * @param page
     * @param size
     * @return
     */
    List<PatientDto> fetchPatient(int page, int size);

    /**
     * Method is used to discharge patient
     * @param patientId
     * @return
     */
    Boolean disChargePatient(Long patientId);
}
