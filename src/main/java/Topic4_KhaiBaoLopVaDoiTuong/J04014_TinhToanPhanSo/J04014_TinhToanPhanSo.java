package Topic4_KhaiBaoLopVaDoiTuong.J04014_TinhToanPhanSo;

import java.util.Scanner;

class Fraction {
    private long numerator, dennominator;

    public Fraction(long num, long den) {
        if (den < 0) {
            den = -den;
            num = -num;
        }

        long g = gcd(Math.abs(num), den);
        this.numerator = num / g;
        this.dennominator = den / g;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static Fraction add(Fraction a, Fraction b) {
        return new Fraction(
                a.numerator * b.dennominator + b.numerator * a.dennominator,
                a.dennominator * b.dennominator);
    }

    public static Fraction prod(Fraction a, Fraction b) {
        return new Fraction(
                a.numerator * b.numerator,
                a.dennominator * b.dennominator);
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.dennominator;
    }
}

public class J04014_TinhToanPhanSo {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            Fraction a = new Fraction(scn.nextLong(), scn.nextLong());
            Fraction b = new Fraction(scn.nextLong(), scn.nextLong());

            Fraction sum = Fraction.add(a, b);
            Fraction c = Fraction.prod(sum, sum);
            Fraction d = Fraction.prod(Fraction.prod(a, b), c);

            System.out.println(c + " " + d);
        }
        scn.close();
    }
}
