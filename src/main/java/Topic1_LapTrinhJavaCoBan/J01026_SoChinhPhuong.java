package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01026_SoChinhPhuong {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            int x = (int) Math.sqrt(n);

            System.out.println(x * x == n ? "YES" : "NO");
        }
        scn.close();
    }
}
