package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01005_ChiaTamGiac {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            long n = scn.nextLong();
            long h = scn.nextLong();

            for (int i = 1; i < n; i++)
                System.out.printf("%.6f ", h * Math.sqrt((double) i / n));
            System.out.println();
        }
        scn.close();
    }
}
