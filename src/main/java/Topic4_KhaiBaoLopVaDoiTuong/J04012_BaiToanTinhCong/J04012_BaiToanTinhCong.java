package Topic4_KhaiBaoLopVaDoiTuong.J04012_BaiToanTinhCong;

import java.util.Scanner;

class Staff {
    private static int count = 1;

    private String id;
    private String name;
    private long salaryBase;
    private long workDay;
    private String role;

    private long salaryMonth;
    private long bonus;
    private long allowence;
    private long total;

    public Staff(String name, long salaryBase, long workDay, String role) {
        this.id = String.format("NV%02d", count++);

        this.name = name;
        this.salaryBase = salaryBase;
        this.workDay = workDay;
        this.role = role;

        this.salaryMonth = salaryBase * workDay;
        this.bonus = this.calculateBonus();
        this.allowence = this.calculateAllowence();
        this.total = this.salaryMonth + this.bonus + this.allowence;
    }

    public long calculateBonus() {
        if (this.workDay >= 25)
            return this.salaryMonth * 20 / 100;
        if (this.workDay >= 22)
            return this.salaryMonth * 10 / 100;
        return 0L;
    }

    public long calculateAllowence() {
        switch (this.role) {
            case "GD":
                return 250000;
            case "PGD":
                return 200000;
            case "TP":
                return 180000;
            case "NV":
                return 150000;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %d",
                this.id, this.name,
                this.salaryMonth, this.bonus, this.allowence, this.total);
    }
}

public class J04012_BaiToanTinhCong {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String name = scn.nextLine();
        long salaryBase = Long.parseLong(scn.next());
        long workDay = Long.parseLong(scn.next());
        String role = scn.next();

        scn.close();
        System.out.println(new Staff(name, salaryBase, workDay, role));
    }
}
