package Topic2_Mang;

import java.util.Scanner;

public class J02014_DiemCanBang {
    static int solve(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];

        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + arr[i];

        for (int i = 1; i < sum.length - 1; i++) {
            if (sum[i - 1] == sum[sum.length - 1] - sum[i])
                return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++)
                arr[i] = scn.nextInt();

            System.out.println(solve(arr));
        }
        scn.close();
    }
}
