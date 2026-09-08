package Topic2_Mang;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class J02007DemSoLanXuatHien {
    static Scanner scn = new Scanner(System.in);

    static void solve() {
        int n = scn.nextInt();
        Map<Integer, Integer> freq = new LinkedHashMap<>();

        while (n-- > 0) {
            int k = scn.nextInt();
            freq.put(k, freq.getOrDefault(k, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet())
            System.out.println(entry.getKey() + " xuat hien " + entry.getValue() + " lan");
    }

    public static void main(String[] arg) {
        int t = scn.nextInt();

        for (int i = 1; i <= t; i++) {
            System.out.println("Test " + i + ":");
            solve();
        }
        scn.close();
    }
}
