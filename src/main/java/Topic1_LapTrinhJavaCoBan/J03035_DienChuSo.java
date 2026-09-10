package Topic1_LapTrinhJavaCoBan;

import java.util.Scanner;

public class J03035_DienChuSo {
    static int[] countQues(String str) {
        int[] ques = new int[str.length() + 1];

        for (int i = str.length() - 1; i >= 0; i--) {
            ques[i] = str.charAt(i) == '?' ? ques[i + 1] + 1 : ques[i + 1];
        }
        return ques;
    }

    static long pow10(int exp) {
        long res = 1;

        for (int i = 1; i <= exp; i++)
            res *= 10;
        return res;
    }

    static long solve(String a, String b) {
        int[] ques = countQues(a);
        long counter = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '?')
                counter += (long) (9 - (b.charAt(i) - '0')) * pow10(ques[i + 1]);

            else {
                if (a.charAt(i) - '0' > b.charAt(i) - '0')
                    return counter + pow10(ques[i + 1]);

                else if (a.charAt(i) - '0' < b.charAt(i) - '0')
                    return counter;
            }
        }
        return counter;
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        scn.nextLine();

        while (t-- > 0)
            System.out.println(solve(scn.nextLine(), scn.nextLine()));

        scn.close();
    }
}
