package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point nextPoint(Scanner sc) {
        return new Point(sc.nextDouble(), sc.nextDouble());
    }

    public double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;

        return Math.sqrt(dx * dx + dy * dy);
    }
}

class Triangle {
    private Point x, y, z;
    private double a, b, c;

    public Triangle(Point x, Point y, Point z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.a = x.distance(y);
        this.b = x.distance(z);
        this.c = y.distance(z);
    }

    public boolean valid() {
        return this.a + this.b > this.c
                && this.a + this.c > this.b
                && this.b + this.c > this.a;
    }

    public String getPerimeter() {
        return String.format("%.3f", a + b + c);
    }
}

public class J04019_LopTriangle1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            Triangle a = new Triangle(Point.nextPoint(sc), Point.nextPoint(sc), Point.nextPoint(sc));
            if (!a.valid()) {
                System.out.println("INVALID");
            } else {
                System.out.println(a.getPerimeter());
            }
        }
    }
}
