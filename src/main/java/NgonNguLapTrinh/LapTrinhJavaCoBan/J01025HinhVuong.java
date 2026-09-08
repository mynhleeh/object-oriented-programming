package NgonNguLapTrinh.LapTrinhJavaCoBan;

import java.util.Scanner;

class point {
    int x;
    int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class J01025HinhVuong {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        // rec ab
        point a = new point(scn.nextInt(), scn.nextInt());
        point b = new point(scn.nextInt(), scn.nextInt());

        // rec cd
        point c = new point(scn.nextInt(), scn.nextInt());
        point d = new point(scn.nextInt(), scn.nextInt());

        // rec ef
        point e = new point(Math.min(a.x, c.x), Math.min(a.y, c.y));
        point f = new point(Math.max(b.x, d.x), Math.max(b.y, d.y));

        int edge = Math.max(Math.abs(f.x - e.x), Math.abs(f.y - e.y));
        System.out.println(edge * edge);
        scn.close();
    }
}
