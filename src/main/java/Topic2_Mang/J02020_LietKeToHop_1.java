package Topic2_Mang;

import java.util.Scanner;

public class J02020_LietKeToHop_1 {
    static boolean gen(int n, int k, int[] num) {
        int i = k;
        while (1 <= i && num[i] == n - k + i)
            i--;

        if (i == 0)
            return false;

        num[i]++;
        for (int j = i + 1; j <= n; j++)
            num[j] = num[j - 1] + 1;

        return true;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt(), k = scn.nextInt();
        scn.close();

        int[] num = new int[n + 1];
        for (int i = 1; i <= n; i++)
            num[i] = i;

        int counter = 0;
        do {
            for (int i = 1; i <= k; i++)
                System.out.print(num[i] + " ");

            System.out.println();
            counter++;
        } while (gen(n, k, num));

        System.out.printf("Tong cong co %d to hop", counter);
    }
}
