package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Teacher {
    private String id, role, name;
    private long coef, salaryBase, bonus, total;

    public Teacher(String id, String name, long salaryBase) {
        this.id = id;
        this.name = name;
        this.salaryBase = salaryBase;

        this.role = id.substring(0, 2);
        this.coef = Integer.valueOf(id.substring(2));
        this.bonus = this.calculateAllowence();
        this.total = this.salaryBase * this.coef + this.bonus;
    }

    private long calculateAllowence() {
        switch (this.role) {
            case "HT":
                return 2_000_000;
            case "HP":
                return 900_000;
            case "GV":
                return 500_000;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d",
                this.id, this.name, this.coef, this.bonus, this.total);
    }
}

public class J04015_TinhThuNhapCuaGiaoVien {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println(new Teacher(
                scn.nextLine(),
                scn.nextLine(),
                Long.parseLong(scn.next())));

        scn.close();
    }
}
