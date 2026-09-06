package ptit;

import java.util.Scanner;

public class J01020DayDuCacChuSo {
    static long solve(long n) {
        int mask = 0, i = 1;

        while (true) {
            long current = i * n;

            while (current != 0) {
                int d = (int) current % 10;
                mask |= (1 << d);
                current /= 10;
            }

            if (mask == (1 << 10) - 1)
                return i * n;

            i++;
        }
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            long n = scn.nextLong();

            if (n == 0)
                System.out.println("Impossible");
            else
                System.out.println(solve(n));
        }
        scn.close();
    }
}
