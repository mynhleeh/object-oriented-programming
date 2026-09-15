package Topic2_Mang;

import java.util.Arrays;
import java.util.Scanner;

public class J02016_BoBaSoPytago {
    static boolean solve(int n, long[] arr) {
        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1;

            while (i < j) {
                if (arr[i] * arr[i] + arr[j] * arr[j] > arr[k] * arr[k])
                    j--;
                else if (arr[i] * arr[i] + arr[j] * arr[j] < arr[k] * arr[k])
                    i++;
                else
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        long t = scn.nextLong();
        while (t-- > 0) {
            int n = scn.nextInt();
            long[] arr = new long[n];

            for (int i = 0; i < n; i++)
                arr[i] = scn.nextLong();

            Arrays.sort(arr);
            System.out.println(solve(n, arr) ? "YES" : "NO");
        }
        scn.close();
    }
}
