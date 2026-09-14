package Topic4_KhaiBaoLopVaDoiTuong.J04006_KhaiBaoLopSinhVien;

import java.util.Scanner;

class Student {
    private String id, name, clazz, dob;
    private float gpa;

    public Student(String id, String name, String clazz, String dob, float gpa) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.gpa = gpa;

        if (dob.charAt(2) != '/')
            dob = "0" + dob;
        if (dob.charAt(5) != '/')
            dob = dob.substring(0, 3) + "0" + dob.substring(3);

        this.dob = dob;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %.2f",
                this.id, this.name, this.clazz, this.dob, this.gpa);
    }
}

public class J04006_KhaiBaoLopSinhVien {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
        String clazz = sc.nextLine();
        String dob = sc.nextLine();
        float gpa = sc.nextFloat();

        System.out.println(new Student("B20DCCN001", name, clazz, dob, gpa));
        sc.close();
    }
}
