package org.example.execrise03;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class DAO {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        int surgeryId = 505;

        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                CallableStatement cstmt = conn.prepareCall(sql)
        ) {

            cstmt.setInt(1, surgeryId);


            cstmt.registerOutParameter(2, Types.DECIMAL);


            cstmt.execute();


            BigDecimal totalCost = cstmt.getBigDecimal(2);

            System.out.println("Chi phí phẫu thuật của surgery_id = " + surgeryId + " là: " + totalCost);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}