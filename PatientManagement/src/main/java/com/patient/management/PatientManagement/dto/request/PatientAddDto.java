package com.patient.management.PatientManagement.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PatientAddDto implements Serializable {
    private static final long serialVersionUID = 1436582056949133677L;
    private String name;
    private Long age;
    private Long roomNo;
    private String doctorId;
    private Date admitDate;
    private Float expenses;
}
