package Topic4_KhaiBaoLopVaDoiTuong.J04009_DienTichTamGiac;

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
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public static double distance(Point p1, Point p2) {
        return p1.distance(p2);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}

public class J04009_DienTichTamGiac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            Point x = new Point(sc.nextDouble(), sc.nextDouble());
            Point y = new Point(sc.nextDouble(), sc.nextDouble());
            Point z = new Point(sc.nextDouble(), sc.nextDouble());

            double a = x.distance(y);
            double b = y.distance(z);
            double c = z.distance(x);

            if (a + b > c && a + c > b && b + c > a)
                System.out.printf("%.2f\n",
                        Math.sqrt(a + b + c) * Math.sqrt(a + b - c) * Math.sqrt(b + c - a) * Math.sqrt(c + a - b) / 4);
            else
                System.out.println("INVALID");
        }
        sc.close();
    }
}
