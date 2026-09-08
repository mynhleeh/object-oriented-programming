package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01012UocSoChiaHetCho2 {
    static long count(long n) {
        if (n % 2 != 0)
            return 0;

        long counter = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i % 2 == 0)
                    counter++;
                if (i != n / i && (n / i) % 2 == 0)
                    counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0)
            System.out.println(count(scn.nextLong()));

        scn.close();
    }
}
