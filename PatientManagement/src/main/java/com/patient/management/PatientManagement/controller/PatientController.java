package com.patient.management.PatientManagement.controller;

import com.auth.layer.AuthenticationLib.dto.response.ResponseWrapper;
import com.patient.management.PatientManagement.dto.request.PatientAddDto;
import com.patient.management.PatientManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/add")
    public ResponseWrapper<Object> addPatient(@RequestBody PatientAddDto patientDetails) {
        patientService.addPatient(patientDetails);
        return new ResponseWrapper<>(200, "Success");
    }

    @GetMapping("/find")
    public ResponseWrapper<Object> fetchPatient(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        return new ResponseWrapper<>(200, patientService.fetchPatient(page, size));
    }

    @GetMapping("/discharge")
    public ResponseWrapper<Object> dischargePatient(@RequestParam(name = "id") Long patientId) {
        return new ResponseWrapper<>(200, patientService.disChargePatient(patientId));
    }
}
