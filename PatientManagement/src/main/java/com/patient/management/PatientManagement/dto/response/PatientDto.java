package com.patient.management.PatientManagement.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PatientDto implements Serializable {
    private static final long serialVersionUID = 1436582056949133677L;
    private Long id;
    private String name;
    private Long age;
    private Long roomNo;
    private Date admitDate;
    private Float expenses;
    private Boolean isDischarged;
}
