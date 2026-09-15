package Topic4_KhaiBaoLopVaDoiTuong.J07058_DanhSachMonThi;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Subject {
    private String id, name, format;

    public Subject(String id, String name, String format) {
        this.id = id;
        this.name = name;
        this.format = format;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.format;
    }
}

public class J07058_DanhSachMonThi {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(new File("MONHOC.in"));

        int n = scn.nextInt();
        scn.nextLine();

        Subject[] subjects = new Subject[n];
        for (int i = 0; i < n; i++)
            subjects[i] = new Subject(scn.nextLine(), scn.nextLine(), scn.nextLine());

        Arrays.sort(subjects, (a, b) -> a.getId().compareTo(b.getId()));
        for (Subject s : subjects)
            System.out.println(s);

        scn.close();
    }
}
