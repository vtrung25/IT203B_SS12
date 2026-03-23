package org.example.execrise03;

import org.example.connetDatabase.DBContext;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class SurgeryFeeService {

    public BigDecimal getSurgeryFee(int surgeryId) {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = DBContext.getConnection();

            String sql = "{call GET_SURGERY_FEE(?, ?)}";
            cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, surgeryId);
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            return cstmt.getBigDecimal(2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) cstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBContext.closeConnection(conn);
        }

        return null;
    }
}