package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Student {
    private String name, dob;
    private double totalGrade;

    public Student(String name, String dob, double grd1, double grd2, double grd3) {
        this.name = name;
        this.dob = dob;
        this.totalGrade = grd1 + grd2 + grd3;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.1f", this.name, this.dob, this.totalGrade);
    }
}

public class J04005_KhaiBaoLopThiSinh {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        String name = scn.nextLine();
        String dob = scn.nextLine();

        double grd1 = Double.parseDouble(scn.next());
        double grd2 = Double.parseDouble(scn.next());
        double grd3 = Double.parseDouble(scn.next());

        System.out.println(new Student(name, dob, grd1, grd2, grd3));
        scn.close();
    }
}
