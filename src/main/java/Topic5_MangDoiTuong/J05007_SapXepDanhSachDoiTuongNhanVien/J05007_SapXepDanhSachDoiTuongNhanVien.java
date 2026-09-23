package Topic5_MangDoiTuong.J05007_SapXepDanhSachDoiTuongNhanVien;

import java.util.Arrays;
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

    public String getDob() {
        String[] parts = this.dob.split("/");
        return String.format("%s%s%s", parts[2], parts[1], parts[0]);
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

public class J05007_SapXepDanhSachDoiTuongNhanVien {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        Staff[] staffs = new Staff[n];

        scn.nextLine();
        for (int i = 0; i < n; i++)
            staffs[i] = new Staff(
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine(),
                    scn.nextLine());
        scn.close();

        Arrays.sort(staffs, (a, b) -> a.getDob().compareTo(b.getDob()));
        for (Staff s : staffs)
            System.out.println(s);
    }
}
