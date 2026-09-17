package Topic5_MangDoiTuong.J05004_DanhSachDoiTuongSinhVien_2;

import java.util.Scanner;

class Student {
    private static int counterId = 1;

    private String id, name, clazz, dob;
    private double gpa;

    private String formatName(String str) {
        String[] parts = str.trim().toLowerCase().split("\\s+");
        StringBuilder res = new StringBuilder();

        for (String s : parts)
            res.append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1))
                    .append(" ");

        return res.toString().trim();
    }

    private String formatDate(String str) {
        String[] parts = str.split("/");

        return String.format("%02d/%02d/%04d",
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));
    }

    public Student(String name, String clazz, String dob, double gpa) {
        this.id = String.format("B20DCCN%03d", counterId++);
        this.name = formatName(name);
        this.clazz = clazz;
        this.dob = formatDate(dob);
        this.gpa = gpa;
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

public class J05004_DanhSachDoiTuongSinhVien_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        scn.nextLine();

        while (n-- > 0)
            System.out.println(new Student(
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    Double.parseDouble(scn.nextLine())));
        scn.close();
    }
}
