package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Fraction {
    private final long numerator, denominator;

    public Fraction(long num, long den) {
        long g = gcd(num, den);

        this.numerator = num / g;
        this.denominator = den / g;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}

public class J04003_PhanSo {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        System.out.println(new Fraction(scn.nextLong(), scn.nextLong()));
        scn.close();
    }
}
