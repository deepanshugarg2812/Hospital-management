package com.patient.management.PatientManagement.service.impl;

import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.patient.management.PatientManagement.dao.PatientDao;
import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import com.patient.management.PatientManagement.dto.response.PatientDto;
import com.patient.management.PatientManagement.entity.PatientEntity;
import com.patient.management.PatientManagement.service.PatientService;
import com.patient.management.PatientManagement.validator.PatientValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;
    private final PatientValidator patientValidator;
    @Override
    public void addPatient(PatientAddDto patientAddDto) {
        try {
            log.info("Request came to add the patient {}", patientAddDto);
            patientValidator.validate(patientAddDto, null);
            patientDao.addPatient(patientAddDto);
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occurred while adding the patient", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<PatientDto> fetchPatient(int page, int size) {
        try {
            List<PatientDto> patientDtos = patientDao.fetchPatient(page, size);
            return patientDtos;
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while fetching the patient", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Boolean disChargePatient(Long patientId) {
        try {
            patientDao.dischargePatient(patientId);
            return true;
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while discharging the patient", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
