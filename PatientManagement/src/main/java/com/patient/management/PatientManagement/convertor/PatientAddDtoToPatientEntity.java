package com.patient.management.PatientManagement.convertor;

import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import com.patient.management.PatientManagement.entity.PatientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PatientAddDtoToPatientEntity implements CommonConvertor<PatientAddDto, PatientEntity> {

    @Override
    public PatientAddDto convert(PatientEntity patientEntity) {
        return null;
    }

    @Override
    public PatientEntity convertBack(PatientAddDto patientAddDto) {
        try {
            PatientEntity patient = new PatientEntity();
            BeanUtils.copyProperties(patientAddDto, patient);
            patient.setIsDischarged(Boolean.FALSE);
            patient.setCreationDate(new Date());
            return patient;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while conversion", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
