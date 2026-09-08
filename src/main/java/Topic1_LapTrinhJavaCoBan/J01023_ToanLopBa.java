package Topic1_LapTrinhJavaCoBan;

import java.util.ArrayList;
import java.util.Scanner;

public class J01023_ToanLopBa {
    static ArrayList<String> addList = new ArrayList<>();
    static ArrayList<String> subList = new ArrayList<>();

    static {
        for (int i = 10; i < 100; i++) {
            for (int j = 10; j < 100; j++) {
                if (i + j < 100)
                    addList.add(i + " + " + j + " = " + (i + j));

                if (10 <= i - j)
                    subList.add(i + " - " + j + " = " + (i - j));
            }
        }
    }

    static boolean isSame(String fll, String mss) {
        for (int i = 0; i < 12; i++) {
            if (mss.charAt(i) != '?' && mss.charAt(i) != fll.charAt(i))
                return false;
        }
        return true;
    }

    static String find(String mss) {
        mss = mss.trim();
        if (mss.length() != 12)
            return "WRONG PROBLEM!";

        char op = mss.charAt(3);

        if (op == '*' || op == '/')
            return "WRONG PROBLEM!";

        if (op == '+' || op == '?') {
            for (String str : addList) {
                if (isSame(str, mss))
                    return str;
            }
        }

        if (op == '-' || op == '?') {
            for (String str : subList) {
                if (isSame(str, mss))
                    return str;
            }
        }
        return "WRONG PROBLEM!";
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        scn.nextLine();

        while (t-- > 0)
            System.out.println(find(scn.nextLine()));
        scn.close();
    }
}
