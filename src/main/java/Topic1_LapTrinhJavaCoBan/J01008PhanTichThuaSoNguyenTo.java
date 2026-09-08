package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01008PhanTichThuaSoNguyenTo {
    static void decompose(long n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                int counter = 0;

                while (n % i == 0) {
                    n /= i;
                    counter++;
                }

                System.out.print(i + "(" + counter + ") ");
            }
        }

        if (n != 1)
            System.out.print(n + "(1)");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Test " + i + ": ");
            decompose(scn.nextLong());
            System.out.println();
        }

        scn.close();
    }
}
