package Topic5_MangDoiTuong.J05005_DanhSachDoiTuongSinhVien_3;

import java.util.Arrays;
import java.util.Scanner;

class Student {
    private static int counter = 1;

    private String id, name, dob, clazz;
    private double gpa;

    public Student(String name, String clazz, String dob, double gpa) {
        this.id = String.format("B20DCCN%03d", counter++);
        this.name = formatName(name);
        this.clazz = clazz;
        this.dob = formatDate(dob);
        this.gpa = gpa;
    }

    private String formatName(String str) {
        String[] parts = str.toLowerCase().trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String s : parts)
            result.append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1))
                    .append(" ");

        return result.toString().trim();
    }

    private String formatDate(String str) {
        String[] parts = str.split("/");

        return String.format("%02d/%02d/%04d",
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));
    }

    public double getGpa() {
        return this.gpa;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %.2f",
                this.id,
                this.name,
                this.clazz,
                this.dob,
                this.gpa);
    }
}

public class J05005_DanhSachDoiTuongSinhVien_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        Student[] students = new Student[n];

        scn.nextLine();
        for (int i = 0; i < n; i++)
            students[i] = new Student(
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    Double.parseDouble(scn.nextLine()));
        scn.close();

        Arrays.sort(students, (a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        for (Student s : students)
            System.out.println(s);
    }
}
