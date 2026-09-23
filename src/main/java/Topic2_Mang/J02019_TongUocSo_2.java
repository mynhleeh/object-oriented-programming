package Topic2_Mang;

import java.util.Scanner;

public class J02019_TongUocSo_2 {
    static int[] init(int MAX) {
        int[] sumDiv = new int[MAX + 1];

        for (int i = 1; i * 2 <= MAX; i++) {
            for (int j = 2 * i; j <= MAX; j += i)
                sumDiv[j] += i;
        }
        return sumDiv;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int a = scn.nextInt();
        int b = scn.nextInt();
        scn.close();

        int[] sumDiv = init(b);
        int counter = 0;

        for (int i = a; i <= b; i++) {
            if (sumDiv[i] > i)
                counter++;
        }
        System.out.println(counter);
    }
}
