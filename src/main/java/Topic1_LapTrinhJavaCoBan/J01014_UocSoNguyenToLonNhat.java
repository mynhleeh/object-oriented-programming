package Topic1_LapTrinhJavaCoBan;

import java.util.*;

public class J01014_UocSoNguyenToLonNhat {
    static long solve(long n) {
        long maxPrime = -1;

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                maxPrime = i;
                while (n % i == 0)
                    n /= i;
            }
        }
        return n == 1 ? maxPrime : n;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0)
            System.out.println(solve(scn.nextLong()));

        scn.close();
    }
}
