package org.example.exerise05;

import java.math.BigDecimal;
import java.util.List;

public class PatientService {
    private final PatientDAO patientDAO = new PatientDAO();

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public boolean addPatient(Patient patient) {
        if (patient.getFullName() == null || patient.getFullName().trim().isEmpty()) {
            return false;
        }
        if (patient.getAge() <= 0 || patient.getDaysAdmitted() < 0) {
            return false;
        }
        return patientDAO.addPatient(patient);
    }

    public boolean updateDisease(int patientId, String newDisease) {
        return patientDAO.updateDiseaseById(patientId, newDisease);
    }

    public BigDecimal calculateFee(int patientId) {
        return patientDAO.calculateDischargeFee(patientId);
    }
}