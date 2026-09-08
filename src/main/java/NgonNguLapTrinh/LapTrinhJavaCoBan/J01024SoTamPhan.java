package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01024SoTamPhan {
    static boolean solve(String str) {
        for (char c : str.toCharArray()) {
            if (c != '0' && c != '1' && c != '2')
                return false;
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
