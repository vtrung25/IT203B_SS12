package org.example.execrise02;

import org.example.connetDatabase.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatinesDAO {
    public void updateTemp(String patient_id, double temp){
        try(
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE Patients set temp = ? where patient_id = ?")
                ){
            ps.setDouble(1, temp);
            ps.setString(2, patient_id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật nhiệt độ thành công");
            } else {
                System.out.println("Không tìm thấy bệnh nhân");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
