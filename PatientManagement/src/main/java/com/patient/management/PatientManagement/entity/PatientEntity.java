package com.patient.management.PatientManagement.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Accessors(chain = true)
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientsequence")
    @SequenceGenerator(name = "patientsequence", sequenceName = "PatientSequence", initialValue = 1)
    private Long id;
    private String name;
    private Long age;
    private Long roomNo;
    private String doctorId;
    private Date admitDate;
    private Float expenses;
    private Boolean isDischarged;
    private Date creationDate;
}
