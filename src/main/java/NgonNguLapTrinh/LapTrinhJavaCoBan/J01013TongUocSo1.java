package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.*;

public class J01013TongUocSo1 {
    static int MAX = 2_000_000;
    static long minPrime[] = new long[MAX + 1];

    static void init() {
        for (int i = 2; i * i <= MAX; i++) {
            if (minPrime[i] == 0) {
                minPrime[i] = i;

                for (int j = i * i; j <= MAX; j += i) {
                    if (minPrime[j] == 0)
                        minPrime[j] = i;
                }
            }
        }
        for (int i = 2; i <= MAX; i++) {
            if (minPrime[i] == 0)
                minPrime[i] = i;
        }
    }

    public static void main(String[] args) {
        init();
        Scanner scn = new Scanner(System.in);

        long sum = 0;
        long n = scn.nextLong();

        while (n-- > 0) {
            int m = scn.nextInt();

            while (m != 1) {
                sum += minPrime[m];
                m /= minPrime[m];
            }
        }

        System.out.println(sum);
        scn.close();
    }
}
