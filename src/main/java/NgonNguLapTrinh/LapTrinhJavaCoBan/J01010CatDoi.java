package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.*;

public class J01010CatDoi {
    static char transform(char c) {
        switch (c) {
            case '0':
                return '0';
            case '1':
                return '1';
            case '8':
                return '0';
            case '9':
                return '0';
            default:
                return 'X';
        }
    }

    static String cut(String str) {
        long num = 0;

        for (char c : str.toCharArray()) {
            char t = transform(c);

            if (t == 'X')
                return "INVALID";
            num = num * 10 + (t - '0');
        }

        return num == 0 ? "INVALID" : String.valueOf(num);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        scn.nextLine();

        while (t-- > 0)
            System.out.println(cut(scn.nextLine()));
        scn.close();
    }
}
