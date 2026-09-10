package Topic2_Mang;

import java.util.Scanner;

public class J02008_BoiSoNhoNhatCuaNSoNguyenDuongDauTien {
    static final int MAX = 100;
    static long[] f = new long[MAX + 1];

    static long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    static {
        f[1] = 1L;

        for (int i = 2; i <= MAX; i++)
            f[i] = lcm(f[i - 1], (long) i);
    }

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0)
            System.out.println(f[sc.nextInt()]);

        sc.close();
    }
}
