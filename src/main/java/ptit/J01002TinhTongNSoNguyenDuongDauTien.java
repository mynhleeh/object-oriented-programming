package ptit;

import java.util.Scanner;

public class J01002TinhTongNSoNguyenDuongDauTien {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            long n = scn.nextInt();
            System.out.println((n + 1) * n / 2);
        }
        scn.close();
    }
}
