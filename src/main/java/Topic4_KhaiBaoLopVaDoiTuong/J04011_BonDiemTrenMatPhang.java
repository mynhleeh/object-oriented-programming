package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Point3D {
    private int x, y, z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point3D vector(Point3D a, Point3D b) {
        return new Point3D(b.x - a.x, b.y - a.y, b.z - a.z);
    }

    public static Point3D crossProd(Point3D a, Point3D b) {
        return new Point3D(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }

    public static int dotProd(Point3D a, Point3D b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static boolean check(Point3D a, Point3D b, Point3D c, Point3D d) {
        Point3D ab = Point3D.vector(a, b);
        Point3D ac = Point3D.vector(a, c);
        Point3D ad = Point3D.vector(a, d);

        Point3D x = Point3D.crossProd(ab, ac);

        return Point3D.dotProd(x, ad) == 0;
    }
}

public class J04011_BonDiemTrenMatPhang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            Point3D p1 = new Point3D(sc.nextInt(), sc.nextInt(), sc.nextInt());
            Point3D p2 = new Point3D(sc.nextInt(), sc.nextInt(), sc.nextInt());
            Point3D p3 = new Point3D(sc.nextInt(), sc.nextInt(), sc.nextInt());
            Point3D p4 = new Point3D(sc.nextInt(), sc.nextInt(), sc.nextInt());

            if (Point3D.check(p1, p2, p3, p4)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
