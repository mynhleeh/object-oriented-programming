package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class fraction {
    private long numerator, denominator;

    public fraction(long num, long den) {
        this.numerator = num;
        this.denominator = den;
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String show() {
        long GCD = gcd(this.numerator, this.denominator);

        this.numerator /= GCD;
        this.denominator /= GCD;

        return this.numerator + "/" + this.denominator;
    }
}

public class J04003_PhanSo {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        fraction f = new fraction(scn.nextLong(), scn.nextLong());
        System.out.println(f.show());

        scn.close();
    }
}
