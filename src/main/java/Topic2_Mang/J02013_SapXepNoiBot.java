package Topic2_Mang;

import java.util.Scanner;

public class J02013_SapXepNoiBot {
    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i])
                return false;
        }
        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        scn.close();

        int step = 1;
        while (!isSorted(arr)) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i])
                    swap(arr, i - 1, i);
            }

            System.out.print("Buoc " + step++ + ": ");
            for (int val : arr)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}
