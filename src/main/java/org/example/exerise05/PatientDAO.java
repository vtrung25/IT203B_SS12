package org.example.exerise05;


import org.example.connetDatabase.DBContext;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM Patients";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("disease"),
                        rs.getInt("days_admitted")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO Patients(full_name, age, department, disease, days_admitted) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, patient.getFullName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getDepartment());
            ps.setString(4, patient.getDisease());
            ps.setInt(5, patient.getDaysAdmitted());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDiseaseById(int patientId, String newDisease) {
        String sql = "UPDATE Patients SET disease = ? WHERE patient_id = ?";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, newDisease);
            ps.setInt(2, patientId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public BigDecimal calculateDischargeFee(int patientId) {
        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (
                Connection conn = DBContext.getConnection();
                CallableStatement cs = conn.prepareCall(sql)
        ) {
            cs.setInt(1, patientId);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();

            return cs.getBigDecimal(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}