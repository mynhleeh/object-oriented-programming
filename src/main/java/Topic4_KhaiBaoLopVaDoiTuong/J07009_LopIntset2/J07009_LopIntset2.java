package Topic4_KhaiBaoLopVaDoiTuong.J07009_LopIntset2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class IntSet {
    private Set<Integer> set;

    public IntSet(Set<Integer> set) {
        this.set = new TreeSet<>(set);
    }

    public IntSet(int[] arr) {
        this.set = new TreeSet<>();
        for (int val : arr)
            set.add(val);
    }

    public IntSet intersection(IntSet other) {
        IntSet result = new IntSet(this.set);
        result.set.retainAll(other.set);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int val : set)
            str.append(val).append(" ");

        return str.toString().trim();
    }
}

public class J07009_LopIntset2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("DATA.in"));
        int n = sc.nextInt(), m = sc.nextInt(), a[] = new int[n], b[] = new int[m];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        for (int i = 0; i < m; i++)
            b[i] = sc.nextInt();
        IntSet s1 = new IntSet(a);
        IntSet s2 = new IntSet(b);
        IntSet s3 = s1.intersection(s2);
        System.out.println(s3);
    }
}
