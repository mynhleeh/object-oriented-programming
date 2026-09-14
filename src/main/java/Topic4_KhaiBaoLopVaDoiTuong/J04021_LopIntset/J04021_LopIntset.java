package Topic4_KhaiBaoLopVaDoiTuong.J04021_LopIntset;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class IntSet {
    private Set<Integer> num;

    public IntSet(int[] arr) {
        this.num = new TreeSet<>();

        for (int val : arr)
            this.num.add(val);
    }

    public IntSet(Set<Integer> setVal) {
        this.num = new TreeSet<>(setVal);
    }

    public IntSet union(IntSet other) {
        IntSet result = new IntSet(this.num);
        result.num.addAll(other.num);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int val : this.num)
            res.append(val).append(" ");

        return res.toString().trim();
    }
}

public class J04021_LopIntset {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), a[] = new int[n], b[] = new int[m];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        for (int i = 0; i < m; i++)
            b[i] = sc.nextInt();
        IntSet s1 = new IntSet(a);
        IntSet s2 = new IntSet(b);
        IntSet s3 = s1.union(s2);
        System.out.println(s3);
    }
}
