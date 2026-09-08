package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01006TinhSoFibonacci {
    static long[] fib = new long[93];

    static void create() {
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < 93; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
    }

    public static void main(String[] arg) {
        create();
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0)
            System.out.println(fib[scn.nextInt()]);

        scn.close();
    }
}
