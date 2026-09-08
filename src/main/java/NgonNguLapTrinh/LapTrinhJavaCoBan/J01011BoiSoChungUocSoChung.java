package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01011BoiSoChungUocSoChung {
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            long a = scn.nextLong();
            long b = scn.nextLong();

            System.out.println(lcm(a, b) + " " + gcd(a, b));
        }

        scn.close();
    }
}
