package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J01016_ChuSo4VaChuSo7 {
    static boolean solve(String str) {
        int four = 0, seven = 0;

        for (char c : str.toCharArray()) {
            if (c == '4')
                four++;
            if (c == '7')
                seven++;
        }

        return (four + seven == 4 || four + seven == 7) ? true : false;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println(solve(scn.nextLine()) ? "YES" : "NO");
        scn.close();
    }
}
