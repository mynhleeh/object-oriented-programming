package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Candidate {
    private String id, name;
    private double math, physic, chemistry;

    private double prior, total;
    private boolean status;

    public Candidate(String id, String name, double math, double physic, double chemistry) {
        this.id = id;
        this.name = name;
        this.math = math;
        this.physic = physic;
        this.chemistry = chemistry;

        this.prior = lookUp(id.substring(0, 3));
        this.total = math * 2 + physic + chemistry;
        this.status = this.total + this.prior >= 24 ? true : false;
    }

    private double lookUp(String kv) {
        switch (kv) {
            case "KV1":
                return 0.5f;
            case "KV2":
                return 1.0f;
            case "KV3":
                return 2.5f;
            default:
                break;
        }
        return 0f;
    }

    private String formtScr(double scr) {
        if (scr == (int) scr)
            return String.valueOf((int) scr);

        return String.format("%.1f", scr);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.id).append(" ")
                .append(this.name).append(" ")
                .append(formtScr(this.prior)).append(" ")
                .append(formtScr(this.total)).append(" ")
                .append(this.status ? "TRUNG TUYEN" : "TRUOT")
                .toString();
    }
}

public class J04013_BaiToanTuyenSinh {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String id = scn.nextLine();
        String name = scn.nextLine();

        double math = Double.parseDouble(scn.nextLine());
        double physic = Double.parseDouble(scn.nextLine());
        double chemistry = Double.parseDouble(scn.nextLine());

        scn.close();
        System.out.println(new Candidate(id, name, math, physic, chemistry));
    }
}
