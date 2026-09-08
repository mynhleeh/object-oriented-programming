package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Point {
    private double x;
    private double y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distance(Point secondPoint) {
        return Math.sqrt((secondPoint.x - this.x) * (secondPoint.x - this.x) +
                (secondPoint.y - this.y) * (secondPoint.y - this.y));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

public class J04001_KhaiBaoLopPoint {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            Point a = new Point(scn.nextDouble(), scn.nextDouble());
            Point b = new Point(scn.nextDouble(), scn.nextDouble());

            System.out.printf("%.4f\n", Point.distance(a, b));
        }
        scn.close();
    }
}
