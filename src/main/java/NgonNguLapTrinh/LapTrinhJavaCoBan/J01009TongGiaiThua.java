package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01009TongGiaiThua {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        long n = scn.nextLong();
        long current = 1, result = 0;

        for (int i = 1; i <= n; i++) {
            current *= i;
            result += current;
        }

        System.out.println(result);
        scn.close();
    }
}
