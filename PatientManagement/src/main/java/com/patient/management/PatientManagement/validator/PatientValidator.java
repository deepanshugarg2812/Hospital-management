package com.patient.management.PatientManagement.validator;

import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class PatientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PatientAddDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (Objects.isNull(target)) {
            throw new ApplicationRuntimeException("Invalid request", HttpStatus.BAD_REQUEST.value());
        }
        PatientAddDto patientAddDto = (PatientAddDto) target;
        if (Objects.isNull(patientAddDto.getAdmitDate()) ||
                Objects.isNull(patientAddDto.getAge()) || Objects.isNull(patientAddDto.getExpenses()) ||
                Objects.isNull(patientAddDto.getRoomNo())) {
            throw new ApplicationRuntimeException("Invalid request", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isAnyBlank(patientAddDto.getDoctorId(), patientAddDto.getName())) {
            throw new ApplicationRuntimeException("Invalid request", HttpStatus.BAD_REQUEST.value());
        }
    }
}
