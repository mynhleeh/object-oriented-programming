package Topic4_KhaiBaoLopVaDoiTuong.J05063_TongDaThuc;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DaThuc {
    private static final Pattern PATTERN = Pattern.compile("(\\d+)\\*x\\^(\\d+)");

    private int size;
    private int[] base;

    public DaThuc(int size) {
        this.size = size;
        this.base = new int[this.size];
    }

    public DaThuc(String str) {
        this.size = -1;
        Matcher matcher = PATTERN.matcher(str);

        while (matcher.find()) {
            int b = Integer.parseInt(matcher.group(1));
            int exp = Integer.parseInt(matcher.group(2));

            if (this.size == -1) {
                this.size = exp + 1;
                this.base = new int[this.size];
            }

            this.base[exp] = b;
        }
    }

    public DaThuc cong(DaThuc other) {
        DaThuc result = new DaThuc(Math.max(this.size, other.size));

        for (int i = 0; i < this.size; i++)
            result.base[i] += this.base[i];

        for (int i = 0; i < other.size; i++)
            result.base[i] += other.base[i];

        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = this.size - 1; i >= 0; i--) {
            if (this.base[i] != 0) {
                if (str.length() > 0)
                    str.append(" + ");

                str.append(this.base[i]).append("*x^").append(i);
            }
        }
        return str.toString();
    }
}

public class J05063_TongDaThuc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            DaThuc p = new DaThuc(sc.nextLine());
            DaThuc q = new DaThuc(sc.nextLine());
            DaThuc r = p.cong(q);
            System.out.println(r);
        }
    }
}
