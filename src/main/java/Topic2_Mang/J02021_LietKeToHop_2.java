package Topic2_Mang;

import java.util.Scanner;

public class J02021_LietKeToHop_2 {
    static int n, k, counter = 0;
    static int[] num;

    static String show() {
        StringBuilder str = new StringBuilder();

        for (int i = 1; i <= k; i++)
            str.append(num[i]);
        return str.toString();
    }

    static void gen(int idx) {
        for (int i = Math.max(num[idx - 1] + 1, idx); i <= n - k + idx; i++) {
            num[idx] = i;

            if (idx == k) {
                System.out.print(show() + " ");
                counter++;
            } else
                gen(idx + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        n = scn.nextInt();
        k = scn.nextInt();
        scn.close();

        num = new int[k + 1];
        gen(1);
        System.out.printf("\nTong cong co %d to hop", counter);
    }
}
