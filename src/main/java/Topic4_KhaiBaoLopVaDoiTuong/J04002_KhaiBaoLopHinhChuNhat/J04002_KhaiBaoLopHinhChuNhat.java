package Topic4_KhaiBaoLopVaDoiTuong.J04002_KhaiBaoLopHinhChuNhat;

import java.util.Scanner;

class Rectangle {
    private double width, height;
    private String color;

    public Rectangle() {
        this.width = 1;
        this.height = 1;
    }

    public Rectangle(double width, double height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return this.color.substring(0, 1).toUpperCase() + this.color.substring(1).toLowerCase();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double findArea() {
        return this.height * this.width;
    }

    public double findPerimeter() {
        return (this.height + this.width) * 2;
    }
}

public class J04002_KhaiBaoLopHinhChuNhat {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        double w = scn.nextDouble();
        double h = scn.nextDouble();

        String color = scn.next();
        scn.close();

        if (w <= 0 || h <= 0)
            System.out.println("INVALID");
        else {
            Rectangle rec = new Rectangle(w, h, color);
            System.out.println((int) rec.findPerimeter() + " " +
                    (int) rec.findArea() + " " +
                    rec.getColor());
        }
    }
}
