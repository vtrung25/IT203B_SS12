package org.example.execrise01;

import org.example.connetDatabase.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DocoterDAO {

    public boolean LoginDocoter(String doctorCode, String password) {
        String sql = "SELECT * from Doctors where  doctor_code = ? AND password = ?";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, doctorCode);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
