package Topic1_LapTrinhJavaCoBan;

import java.math.BigInteger;
import java.util.Scanner;

public class J03033BoiSoChungNhoNhat {
    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while (t-- > 0) {
            BigInteger a = new BigInteger(scn.next());
            BigInteger b = new BigInteger(scn.next());

            BigInteger gcd = a.gcd(b);
            System.out.println(a.divide(gcd).multiply(b));
        }
        scn.close();
    }
}
