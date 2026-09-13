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

    public String mul(Matrix other) {
        Matrix result = new Matrix(this.n, other.m);

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < other.m; j++) {
                for (int k = 0; k < this.m; k++)
                    result.mat[i][j] += this.mat[i][k] * other.mat[k][j];
            }
        }

        StringBuilder output = new StringBuilder();

        for (int[] row : result.mat) {
            for (int e : row)
                output.append(e).append(" ");
            output.append("\n");
        }
        return output.toString();
    }
}

public class J04016_TichHaiDoiTuongMaTran {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), p = sc.nextInt();
        Matrix a = new Matrix(n, m);
        a.nextMatrix(sc);
        Matrix b = new Matrix(m, p);
        b.nextMatrix(sc);
        System.out.println(a.mul(b));
    }
}
