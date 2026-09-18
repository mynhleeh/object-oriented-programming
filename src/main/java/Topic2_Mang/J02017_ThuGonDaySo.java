package Topic2_Mang;

import java.util.Scanner;

public class J02017_ThuGonDaySo {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] stack = new int[n];
        int top = -1;

        while (n-- > 0) {
            int val = scn.nextInt();

            if (top >= 0 && (stack[top] + val) % 2 == 0)
                top--;
            else
                stack[++top] = val;
        }

        scn.close();
        System.out.println(top + 1);
    }
}
