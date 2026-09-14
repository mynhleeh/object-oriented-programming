package Topic4_KhaiBaoLopVaDoiTuong.J04022_Wordset;

import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class WordSet {
    private Set<String> setStr;

    public WordSet(String str) {
        this.setStr = new TreeSet<>();

        StringTokenizer strTok = new StringTokenizer(str.toLowerCase());
        while (strTok.hasMoreTokens())
            this.setStr.add(strTok.nextToken());
    }

    public WordSet(Set<String> set) {
        this.setStr = set;
    }

    public WordSet union(WordSet other) {
        Set<String> uniSet = new TreeSet<>(this.setStr);
        uniSet.addAll(other.setStr);

        return new WordSet(uniSet);
    }

    public WordSet intersection(WordSet other) {
        Set<String> intSet = new TreeSet<>(this.setStr);
        intSet.retainAll(other.setStr);

        return new WordSet(intSet);
    }

    @Override
    public String toString() {
        return String.join(" ", this.setStr);
    }
}

public class J04022_Wordset {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        WordSet s1 = new WordSet(in.nextLine());
        WordSet s2 = new WordSet(in.nextLine());
        System.out.println(s1.union(s2));
        System.out.println(s1.intersection(s2));
    }
}
