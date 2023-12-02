package com.patient.management.PatientManagement.convertor;

import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.patient.management.PatientManagement.dto.response.PatientDto;
import com.patient.management.PatientManagement.entity.PatientEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PatientEntityToPatientDto implements CommonConvertor<PatientEntity, PatientDto> {
    @Override
    public PatientEntity convert(PatientDto patientDto) {
        return null;
    }

    @Override
    public PatientDto convertBack(PatientEntity patientEntity) {
        try {
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(patientEntity, patientDto);
            return patientDto;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while conversion", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
