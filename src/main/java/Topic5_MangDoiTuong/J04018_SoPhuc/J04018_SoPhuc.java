package Topic5_MangDoiTuong.J04018_SoPhuc;

import java.util.Scanner;

class Complex {
    private int real, imagine;

    public Complex(int real, int imagine) {
        this.real = real;
        this.imagine = imagine;
    }

    public Complex add(Complex other) {
        return new Complex(
                this.real + other.real,
                this.imagine + other.imagine);
    }

    public Complex multiply(Complex other) {
        return new Complex(
                this.real * other.real - this.imagine * other.imagine,
                this.real * other.imagine + this.imagine * other.real);
    }

    @Override
    public String toString() {
        char sign = this.imagine >= 0 ? '+' : '-';
        return String.format("%d %c %di", this.real, sign, Math.abs(this.imagine));
    }
}

public class J04018_SoPhuc {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            Complex a = new Complex(scn.nextInt(), scn.nextInt());
            Complex b = new Complex(scn.nextInt(), scn.nextInt());

            Complex sum = a.add(b);
            System.out.println(sum.multiply(a) + ", " + sum.multiply(sum));
        }
        scn.close();
    }
}
