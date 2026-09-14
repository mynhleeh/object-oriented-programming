package Topic4_KhaiBaoLopVaDoiTuong.J04008_ChuViTamGiac;

import java.util.Scanner;

class Point {
    private double x, y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distance(Point sec) {
        return Math.sqrt((sec.x - this.x) * (sec.x - this.x)
                + (sec.y - this.y) * (sec.y - this.y));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x)
                + (p1.y - p2.y) * (p1.y - p2.y));
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}

public class J04008_ChuViTamGiac {
    static double perimeter(Point a, Point b, Point c) {
        double ab = a.distance(b);
        double ac = a.distance(c);
        double bc = b.distance(c);

        if (ab + ac > bc && ab + bc > ac && ac + bc > ab)
            return ab + ac + bc;
        return -1;
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            Point a = new Point(scn.nextDouble(), scn.nextDouble());
            Point b = new Point(scn.nextDouble(), scn.nextDouble());
            Point c = new Point(scn.nextDouble(), scn.nextDouble());

            double p = perimeter(a, b, c);
            System.out.println(p == -1.0 ? "INVALID" : String.format("%.3f", p));
        }
        scn.close();
    }
}
