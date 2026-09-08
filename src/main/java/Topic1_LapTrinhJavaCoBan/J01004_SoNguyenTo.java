package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01004_SoNguyenTo {
    static boolean prime(int n) {
        if (n < 2)
            return false;
        if (n == 2)
            return true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            if (prime(scn.nextInt()))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        scn.close();
    }
}
