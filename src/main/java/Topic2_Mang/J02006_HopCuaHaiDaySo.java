package Topic2_Mang;

import java.util.*;

public class J02006_HopCuaHaiDaySo {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int m = scn.nextInt();

        Set<Integer> res = new TreeSet<>();
        while (n-- > 0)
            res.add(scn.nextInt());
        while (m-- > 0)
            res.add(scn.nextInt());

        scn.close();
        for (Integer i : res)
            System.out.print(i + " ");
    }
}
