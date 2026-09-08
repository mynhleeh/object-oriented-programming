package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01017SoLienKe {
    static boolean solve(String str) {
        int prev = str.charAt(0) - '0';

        for (int i = 1; i < str.length(); i++) {
            int cur = str.charAt(i) - '0';

            if (Math.abs(cur - prev) != 1)
                return false;

            prev = cur;
        }
        return true;
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        scn.nextLine();

        while (t-- > 0)
            System.out.println(solve(scn.nextLine()) ? "YES" : "NO");

        scn.close();
    }
}
