package org.example.exerise05;


public class Patient {
    private int patientId;
    private String fullName;
    private int age;
    private String department;
    private String disease;
    private int daysAdmitted;

    public Patient() {
    }

    public Patient(int patientId, String fullName, int age, String department, String disease, int daysAdmitted) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.disease = disease;
        this.daysAdmitted = daysAdmitted;
    }

    public Patient(String fullName, int age, String department, String disease, int daysAdmitted) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.disease = disease;
        this.daysAdmitted = daysAdmitted;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getDaysAdmitted() {
        return daysAdmitted;
    }

    public void setDaysAdmitted(int daysAdmitted) {
        this.daysAdmitted = daysAdmitted;
    }

    @Override
    public String toString() {
        return "Ma BN: " + patientId +
                " | Ten: " + fullName +
                " | Tuoi: " + age +
                " | Khoa: " + department +
                " | Benh ly: " + disease +
                " | So ngay nam vien: " + daysAdmitted;
    }
}