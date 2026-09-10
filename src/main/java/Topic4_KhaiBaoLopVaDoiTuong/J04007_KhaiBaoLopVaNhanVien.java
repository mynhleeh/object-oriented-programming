package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Staff {
    private String id, name, gender, dob;
    private String addr, tax, sign;

    public Staff(String id, String name, String gender, String dob,
            String addr, String tax, String sign) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.addr = addr;
        this.tax = tax;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s", this.id, this.name, this.gender,
                this.dob, this.addr, this.tax, this.sign);
    }
}

public class J04007_KhaiBaoLopVaNhanVien {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        System.out.println(new Staff(
                "00001", scn.nextLine(), scn.nextLine(), scn.nextLine(),
                scn.nextLine(), scn.nextLine(), scn.nextLine()));

        scn.close();
    }
}
