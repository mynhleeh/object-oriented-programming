package Topic2_Mang;

import java.util.ArrayList;
import java.util.Scanner;

public class J02012_SapXepChen {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int e = scn.nextInt();

            int j = 0;
            while (j < arr.size() && arr.get(j) < e)
                j++;
            arr.add(j, e);

            System.out.print("Buoc " + i + ": ");
            for (Integer val : arr)
                System.out.print(val + " ");
            System.out.println();
        }
        scn.close();
    }
}
