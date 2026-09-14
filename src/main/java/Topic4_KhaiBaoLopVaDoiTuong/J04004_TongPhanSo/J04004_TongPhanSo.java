package Topic4_KhaiBaoLopVaDoiTuong.J04004_TongPhanSo;

import java.util.Scanner;

class Fraction {
    private long numerator, denominator;

    private static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public Fraction(long num, long den) {
        long g = gcd(num, den);

        this.numerator = num / g;
        this.denominator = den / g;
    }

    public static Fraction sumFrac(Fraction a, Fraction b) {
        return new Fraction(a.numerator * b.denominator + a.denominator * b.numerator,
                a.denominator * b.denominator);
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}

public class J04004_TongPhanSo {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        Fraction a = new Fraction(scn.nextLong(), scn.nextLong());
        Fraction b = new Fraction(scn.nextLong(), scn.nextLong());

        System.out.println(Fraction.sumFrac(a, b));
        scn.close();
    }
}
