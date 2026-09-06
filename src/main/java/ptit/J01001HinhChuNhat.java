package ptit;

import java.util.Scanner;

public class J01001HinhChuNhat {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int len = scn.nextInt();
        int width = scn.nextInt();

        if (len <= 0 || width <= 0)
            System.out.println(0);
        else
            System.out.println(((len + width) * 2) + " " + (len * width));

        scn.close();
    }
}
