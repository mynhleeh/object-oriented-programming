package ptit;

import java.util.Arrays;
import java.util.Scanner;

public class J01007KiemTraSoFibonacci {
    static long[] fib = new long[93];

    static void create() {
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < 93; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
    }

    // static boolean binarySearch(long n) {
    // int l = 0, r = 92;
    // while (l <= r) {
    // int m = (l + r) / 2;

    // if (n < fib[m])
    // r = m - 1;
    // else if (fib[m] < n)
    // l = m + 1;
    // else
    // return true;
    // }

    // return false;
    // }

    public static void main(String[] args) {
        create();
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            // System.out.println(binarySearch(scn.nextLong()) ? "YES" : "NO");

            boolean exist = Arrays.binarySearch(fib, scn.nextLong()) >= 0;
            System.out.println(exist ? "YES" : "NO");
        }

        scn.close();
    }
}