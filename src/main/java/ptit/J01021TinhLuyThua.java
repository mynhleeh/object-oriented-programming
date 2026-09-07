package ptit;

import java.util.Scanner;

public class J01021TinhLuyThua {
    static final long MOD = 1_000_000_000 + 7;

    static long expo(long a, long b) {
        if (b == 0)
            return 1;

        long half = expo(a, b / 2) % MOD;

        if (b % 2 == 0)
            return (half * half) % MOD;
        else
            return (((half * half) % MOD) * a) % MOD;
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        while (true) {
            long a = scn.nextLong();
            long b = scn.nextLong();

            if (a == 0 && b == 0)
                break;

            System.out.println(expo(a % MOD, b));
        }
        scn.close();
    }
}
