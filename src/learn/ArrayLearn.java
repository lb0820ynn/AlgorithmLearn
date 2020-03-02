package learn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubin on 2018/12/7.
 */
public class ArrayLearn {

    public static void main(String[] args) {
//        int minNum = minNumberInRotateArray(new int[]{3, 4, 5, 1, 2});
//        System.out.print("min num == " + minNum);

//        int fibonacci = JumpFloor(5);
        int fibonacci = JumpFloorII(3);
        System.out.print("fibonaci == " + fibonacci);

        int[] array = new int[]{3, 4, 5, 1, 2};
        reOrderArray(array);
        System.out.print("reorderArray == " + array);

        System.out.println();

//        int[][] matrixArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrixArray = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}, {13, 14}, {15, 16}};
//        int[][] matrixArray = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        printMatrix(matrixArray);

        System.out.println();
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2, 1};
        int result = MoreThanHalfNum_Solution(arr);
        System.out.print(result);

        System.out.println();

        System.out.print(Power(2, 5));

    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array
     * @return
     */
    public static int minNumberInRotateArray(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }

        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (array[left] >= array[right]) {
            if (right - left == 1) {
                return array[right];
            }

            mid = (left + right) / 2;
            if (array[mid] > array[left]) {
                left = mid;
            } else if (array[mid] < array[left]) {
                right = mid;
            } else {
                return minNum(array, left, right);
            }
        }

        return array[mid];
    }

    private static int minNum(int[] array, int left, int right) {
        int min = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < 2) {
            return 1;
        }

        int num = 0;
        int num1 = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = num + num1;
            num = num1;
            num1 = result;
        }
        return result;
//        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * @param target
     * @return
     */
    public static int JumpFloor(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target < 2) {
            return 1;
        }
        int num = 1;
        int num1 = 1;
        int result = 0;
        for (int i = 2; i <= target; i++) {
            result = num + num1;
            num = num1;
            num1 = result;
        }
        return result;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * @param target
     * @return
     */
    public static int JumpFloorII(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target < 2) {
            return 1;
        }

        return 1 << (target - 1);
//        return 2 * JumpFloorII(target - 1);
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * @param array
     */
    public static void reOrderArray(int[] array) {
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                evenList.add(array[i]);
            } else {
                oddList.add(array[i]);
            }
        }

        for (int j = 0; j < oddList.size(); j++) {
            array[j] = oddList.get(j);
        }
        int endIndexOdd = oddList.size();
        for (int k = 0; k < evenList.size(); k++) {
            array[k + endIndexOdd] = evenList.get(k);
        }
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


    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        int times = 1;
        int result = array[0];

        for (int j = 1; j < array.length; j++) {
            if (times == 0) {
                result = array[j];
                times = 1;
            } else {
                if (array[j] == result) {
                    times++;
                } else {
                    times--;
                }
            }
        }
        int realTime = 0;
        for (int k = 0; k < array.length; k++) {
            if (result == array[k]) {
                realTime++;
            }
        }
        if (realTime * 2 <= array.length) {
            return 0;
        } else {
            return result;
        }

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
