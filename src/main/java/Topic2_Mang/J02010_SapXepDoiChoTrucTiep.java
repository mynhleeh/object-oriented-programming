package Topic2_Mang;

import java.util.Scanner;

public class J02010_SapXepDoiChoTrucTiep {
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void show(int[] arr) {
        for (int val : arr)
            System.out.print(val + " ");

        System.out.println();
    }

    public static void main(String[] arg) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        scn.close();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j])
                    swap(arr, i, j);
            }

            System.out.print("Buoc " + (i + 1) + ": ");
            show(arr);
        }
    }
}