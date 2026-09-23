package Topic5_MangDoiTuong.J05008_DienTichDaGiac;

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

    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx - dy * dy);
    }

    public static double distance(Point p1, Point p2) {
        return p1.distance(p2);
    }

    @Override
    public String toString() {
        return String.format("%.3f %.3f", this.x, this.y);
    }
}

public class J05008_DienTichDaGiac {
    static void solve(Scanner scn) {
        int n = scn.nextInt();
        Point[] points = new Point[n];

        double area = 0;
        for (int i = 0; i < n; i++) {
            points[i] = new Point(scn.nextDouble(), scn.nextDouble());

            if (i > 0) {
                area += points[i - 1].getX() * points[i].getY() -
                        points[i].getX() * points[i - 1].getY();
            }
        }

        area += points[n - 1].getX() * points[0].getY() -
                points[0].getX() * points[n - 1].getY();

        System.out.printf("%.3f\n", Math.abs(area) / 2);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0)
            solve(scn);

        scn.close();
    }
}
