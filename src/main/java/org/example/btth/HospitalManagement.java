package org.example.btth;

import java.sql.*;
import java.util.Scanner;
public class HospitalManagement {
    static final String URL = "jdbc:mysql://localhost:3306/hospital";
    static final String USER = "root";
    static final String PASS = "250906";

    public static void updateMedicineStock(int id, int addedQuantity) {
        String sql = "UPDATE medicines SET stock = stock + ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, addedQuantity);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Cập nhật kho thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findMedicinesByPriceRange(double min, double max) {
        String sql = "SELECT * FROM medicines WHERE price BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getDouble("price"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void calculatePrescriptionTotal(int id) {
        String sql = "{call CalculatePrescriptionTotal(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();
            System.out.println("Tổng tiền: " + cs.getDouble(2));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getDailyRevenue(String date) {
        String sql = "{call GetDailyRevenue(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, date);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();
            System.out.println("Doanh thu: " + cs.getDouble(2));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        updateMedicineStock(1, 10);
        findMedicinesByPriceRange(5, 25);
        calculatePrescriptionTotal(1);
        getDailyRevenue("2026-03-20");
    }
}