package learn.other;


import com.sun.tools.javac.util.ArrayUtils;
import learn.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by liubin on 2018/12/7.
 */
public class ArrayLearn {

    public static void main(String[] args) {

        ArrayLearn a = new ArrayLearn();

        int[][] matrixArray = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}, {13, 14}, {15, 16}};
        ArrayList<Integer> list = printMatrix2(matrixArray);
        Utils.printList(list);
    }


    public static ArrayList<Integer> printMatrix2(int[][] matrix) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (matrix == null || matrix.length <= 0) {
            return arr;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] bs = new boolean[n][m];
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int x = 0, y = 0, d = 1;
        for (int i = 0; i < n * m; i++) {
            arr.add(matrix[x][y]);
            bs[x][y] = true;
            int a = x + dx[d];
            int b = y + dy[d];
            if (a < 0 || a >= n || b < 0 || b >= m || bs[a][b]) {
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
        return arr;
    }


    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length <= 0) {
            return result;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        if (row <= 1) {
            for (int m = 0; m < column; m++) {
                result.add(matrix[0][m]);
            }
            return result;
        }

        if (column <= 1) {
            for (int n = 0; n < row; n++) {
                result.add(matrix[n][0]);
            }
            return result;
        }

        int circle = (Math.min(row, column) - 1) / 2 + 1;
        for (int c = 0; c < circle; c++) {
            for (int i = c; i < column - c; i++) {
                System.out.print(matrix[c][i] + ",");
                result.add(matrix[c][i]);
            }
            for (int j = c + 1; j < row - c; j++) {
                System.out.print(matrix[j][column - c - 1] + ",");
                result.add(matrix[j][column - c - 1]);
            }

            for (int k = column - c - 1 - 1; k >= c && row - c - 1 != c; k--) {
                System.out.print(matrix[row - c - 1][k] + ",");
                result.add(matrix[row - c - 1][k]);
            }

            for (int l = row - c - 1 - 1; l >= c + 1 && column - c - 1 != c; l--) {
                System.out.print(matrix[l][c] + ",");
                result.add(matrix[l][c]);
            }
        }
        return result;
    }

    public static double Power(double base, int exponent) {
        if (equal(base, 0.0)) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        int absExponent = Math.abs(exponent);
        double result = getValue4Exponent(base, absExponent);

        return exponent < 0 ? 1 / result : result;
    }

    public static double getValue4Exponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double result = 1;
        double curr = base;


        while (exponent != 0) {
            if ((exponent & 1) == 1)
                result *= curr;
            curr *= curr;// 翻倍
            exponent >>= 1;// 右移一位
        }

//        double result = getValue4Exponent(base, exponent >> 1);
//        result *= result;
//        if ((exponent & 0x1) == 1) {
//            result *= base;
//        }
        return result;
    }


    static boolean equal(double num1, double num2) {
        if (num1 - num2 > -0.000001 && num1 - num2 < 0.000001)
            return true;
        else
            return false;
    }


}
