package NgonNguLapTrinh.Mang;

import java.util.Scanner;

public class J02004MangDoiXung {
    static boolean symmetry(String str) {
        String rev = new StringBuilder(str).reverse().toString();
        return str.equals(rev);
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            scn.nextInt();
            scn.nextLine();
            System.out.println(symmetry(scn.nextLine()) ? "YES" : "NO");
        }
        scn.close();
    }
}
