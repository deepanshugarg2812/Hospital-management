package com.patient.management.PatientManagement.dao.impl;

import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.patient.management.PatientManagement.convertor.CommonConvertor;
import com.patient.management.PatientManagement.dao.PatientDao;
import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import com.patient.management.PatientManagement.dto.response.PatientDto;
import com.patient.management.PatientManagement.entity.PatientEntity;
import com.patient.management.PatientManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientDaoImpl implements PatientDao {
    private final PatientRepository patientRepository;
    private final CommonConvertor<PatientAddDto, PatientEntity> patientAddDtoPatientEntityCommonConvertor;
    private final CommonConvertor<PatientEntity, PatientDto> patientEntityPatientDtoCommonConvertor;

    @Override
    public void addPatient(PatientAddDto patientAddDto) {
        try {
            PatientEntity patient = patientAddDtoPatientEntityCommonConvertor.convertBack(patientAddDto);
            patientRepository.save(patient);
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while adding patient", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<PatientDto> fetchPatient(int page, int size) {
        try {
            PageRequest pageable = PageRequest.of(page, size, Sort.Direction.ASC, "creationDate");
            Page<PatientEntity> patientEntities = patientRepository.findAll(pageable);
            if (patientEntities.hasContent() == Boolean.FALSE) {
                return Collections.EMPTY_LIST;
            }
            return patientEntities.get().map(x ->
                    patientEntityPatientDtoCommonConvertor.convertBack(x)).collect(Collectors.toList());
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while fetching patient", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public void dischargePatient(Long patientId) {
        try {
            if (patientRepository.updatePatientActiveStatus(Boolean.TRUE, patientId) == NumberUtils.INTEGER_ZERO) {
                throw new ApplicationRuntimeException("No patient present with the details", HttpStatus.BAD_REQUEST.value());
            }
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while discharging patient", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
