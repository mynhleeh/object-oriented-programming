package Topic5_MangDoiTuong.J05006_DanhSachDoiTuongNhanVien;

import java.util.Scanner;

class Staff {
    private static int counter = 1;
    private String id, name, gender, dob, addr, tax, dos;

    public Staff(String name, String gender, String dob,
            String addr, String tax, String dos) {
        this.id = String.format("%05d", counter++);
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.addr = addr;
        this.tax = tax;
        this.dos = dos;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s",
                this.id,
                this.name,
                this.gender,
                this.dob,
                this.addr,
                this.tax,
                this.dos);
    }
}

public class J05006_DanhSachDoiTuongNhanVien {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        scn.nextLine();

        while (n-- > 0)
            System.out.println(new Staff(
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine()));
        scn.close();
    }
}
