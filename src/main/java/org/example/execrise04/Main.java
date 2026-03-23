package org.example.execrise04;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TestResult> list = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            list.add(new TestResult("Ket qua xet nghiem so " + i));
        }

        ResultDAO dao = new ResultDAO();

        System.out.println("=== Chay bang Statement ===");
        dao.insertWithStatement(list);

        System.out.println("=== Chay bang PreparedStatement ===");
        dao.insertWithPreparedStatement(list);
    }
}