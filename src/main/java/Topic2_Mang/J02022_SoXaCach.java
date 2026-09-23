package Topic2_Mang;

import java.util.Scanner;

public class J02022_SoXaCach {
    static void solve(int n, int[] num, boolean[] freq, int idx, StringBuilder out) {
        for (int i = 1; i <= n; i++) {
            if (!freq[i] &&
                    (idx == 0 || idx > 0 && Math.abs(num[idx - 1] - i) != 1)) {
                num[idx] = i;
                freq[i] = true;

                if (idx == n - 1) {
                    for (int digit : num)
                        out.append(digit);
                    out.append("\n");
                } else
                    solve(n, num, freq, idx + 1, out);

                freq[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            int[] num = new int[n];

            boolean[] freq = new boolean[n + 1];
            StringBuilder out = new StringBuilder();

            solve(n, num, freq, 0, out);
            System.out.println(out);
        }
        scn.close();
    }
}
