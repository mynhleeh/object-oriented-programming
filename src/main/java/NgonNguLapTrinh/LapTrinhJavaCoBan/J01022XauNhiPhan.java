package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01022XauNhiPhan {
    static long[] fib = new long[93];

    static {
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < 93; i++)
            fib[i] = fib[i - 2] + fib[i - 1];
    }

    static int find(int n, long k) {
        if (n == 1)
            return 0;
        if (n == 2)
            return 1;

        if (k <= fib[n - 2])
            return find(n - 2, k);
        else
            return find(n - 1, k - fib[n - 2]);
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            System.out.println(find(scn.nextInt(), scn.nextLong()));
        }
        scn.close();
    }
}
