package Topic4_KhaiBaoLopVaDoiTuong.J04010_DienTichHinhTronNgoaiTiepTamGiac;

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

    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double distance(Point p1, Point p2) {
        return p1.distance(p2);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}

public class J04010_DienTichHinhTronNgoaiTiepTamGiac {
    static void solve(Point x, Point y, Point z) {
        double a = Point.distance(x, y);
        double b = Point.distance(x, z);
        double c = Point.distance(y, z);

        if (!(a + b > c && a + c > b && b + c > a)) {
            System.out.println("INVALID");
            return;
        }

        double areaTriangle = Math.sqrt((a + b + c) * (a + b - c) * (b + c - a) * (c + a - b)) / 4;
        double r = (a * b * c) / (4 * areaTriangle);
        System.out.printf("%.3f\n", r * r * Math.PI);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            Point x = new Point(scn.nextDouble(), scn.nextDouble());
            Point y = new Point(scn.nextDouble(), scn.nextDouble());
            Point z = new Point(scn.nextDouble(), scn.nextDouble());

            solve(x, y, z);
        }
        scn.close();
    }
}
