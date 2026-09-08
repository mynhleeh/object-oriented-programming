package Topic2_Mang;

import java.util.*;

public class J02005_GiaoCuaHaiDaySo {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int m = scn.nextInt();

        boolean[] existA = new boolean[1000];
        while (n-- > 0)
            existA[scn.nextInt()] = true;

        boolean[] existB = new boolean[1000];
        while (m-- > 0)
            existB[scn.nextInt()] = true;

        scn.close();
        for (int i = 1; i < 1000; i++) {
            if (existA[i] && existB[i])
                System.out.print(i + " ");
        }
    }
}
