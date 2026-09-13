package Topic4_KhaiBaoLopVaDoiTuong;

import java.util.Scanner;

class Matrix {
    private int n, m;
    private int[][] mat;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.mat = new int[n][m];
    }

    public void nextMatrix(Scanner sc) {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++)
                this.mat[i][j] = sc.nextInt();
        }
    }

    public Matrix trans() {
        Matrix result = new Matrix(this.m, this.n);

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++)
                result.mat[j][i] = this.mat[i][j];
        }
        return result;
    }

    public String mul(Matrix other) {
        Matrix result = new Matrix(this.n, other.m);

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < other.m; j++) {
                for (int k = 0; k < this.m; k++)
                    result.mat[i][j] += this.mat[i][k] * other.mat[k][j];
            }
        }

        StringBuilder ouput = new StringBuilder();

        for (int[] row : result.mat) {
            for (int e : row)
                ouput.append(e).append(" ");
            ouput.append("\n");
        }

        return ouput.toString().trim();
    }
}

public class J04017_TichMaTranVaChuyenViCuaNo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt();
            Matrix a = new Matrix(n, m);
            a.nextMatrix(sc);
            Matrix b = a.trans();
            System.out.println(a.mul(b));
        }
    }
}
