package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01018_SoKhongLienKe {
    static boolean solve(String str) {
        int sum = str.charAt(0) - '0';
        int pre = str.charAt(0) - '0';

        for (int i = 1; i < str.length(); i++) {
            int cur = str.charAt(i) - '0';

            if (Math.abs(cur - pre) != 2)
                return false;

            sum += cur;
            pre = cur;
        }

        return sum % 10 == 0 ? true : false;
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
