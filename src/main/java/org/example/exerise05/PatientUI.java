package org.example.exerise05;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PatientUI {
    private final PatientService patientService = new PatientService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;

        do {
            showMenu();
            System.out.print("Nhap lua chon: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showAllPatients();
                    break;
                case 2:
                    addPatient();
                    break;
                case 3:
                    updateDisease();
                    break;
                case 4:
                    dischargeAndCalculateFee();
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private void showMenu() {
        System.out.println("\n===== HE THONG QUAN LY NOI TRU RHMS =====");
        System.out.println("1. Danh sach benh nhan");
        System.out.println("2. Tiep nhan benh nhan moi");
        System.out.println("3. Cap nhat benh an");
        System.out.println("4. Xuat vien va tinh phi");
        System.out.println("5. Thoat");
    }

    private void showAllPatients() {
        List<Patient> list = patientService.getAllPatients();
        if (list.isEmpty()) {
            System.out.println("Khong co benh nhan nao.");
            return;
        }
        for (Patient p : list) {
            System.out.println(p);
        }
    }

    private void addPatient() {
        System.out.print("Nhap ten benh nhan: ");
        String name = scanner.nextLine();

        System.out.print("Nhap tuoi: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhap khoa dieu tri: ");
        String department = scanner.nextLine();

        System.out.print("Nhap benh ly: ");
        String disease = scanner.nextLine();

        System.out.print("Nhap so ngay nam vien: ");
        int days = Integer.parseInt(scanner.nextLine());

        Patient patient = new Patient(name, age, department, disease, days);
        boolean result = patientService.addPatient(patient);

        if (result) {
            System.out.println("Them benh nhan thanh cong.");
        } else {
            System.out.println("Them benh nhan that bai.");
        }
    }

    private void updateDisease() {
        System.out.print("Nhap ma benh nhan can cap nhat: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhap benh ly moi: ");
        String newDisease = scanner.nextLine();

        boolean result = patientService.updateDisease(id, newDisease);

        if (result) {
            System.out.println("Cap nhat benh an thanh cong.");
        } else {
            System.out.println("Khong tim thay benh nhan hoac cap nhat that bai.");
        }
    }

    private void dischargeAndCalculateFee() {
        System.out.print("Nhap ma benh nhan xuat vien: ");
        int id = Integer.parseInt(scanner.nextLine());

        BigDecimal fee = patientService.calculateFee(id);

        if (fee != null) {
            System.out.println("Tong vien phi cua benh nhan ma " + id + " la: " + fee);
        } else {
            System.out.println("Khong tinh duoc vien phi.");
        }
    }
}