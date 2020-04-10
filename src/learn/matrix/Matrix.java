package learn.matrix;

import learn.other.OtherAll;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by liubin on 2020/4/10.
 */
public class Matrix {

    class Point {
        int fir;
        int second;

        public Point(int x, int y) {
            fir = x;
            second = y;
        }
    }

    public int getSingleSum(int p) {
        int s = 0;
        while (p > 0) {
            s += (p % 10);
            p /= 10;
        }
        return s;
    }

    public int getSum(Point p) {
        return getSingleSum(p.fir) + getSingleSum(p.second);
    }

    /**
     * 066-机器人的运动范围
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
     * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] arr = new boolean[rows][cols];
        int res = 0;
        queue.add(new Point(0, 0));

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (getSum(p) > threshold || arr[p.fir][p.second]) {
                continue;
            }
            res++;
            arr[p.fir][p.second] = true;
            for (int i = 0; i < 4; i++) {
                int x = p.fir + dx[i];
                int y = p.second + dy[i];
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    queue.add(new Point(x, y));
                }
            }
        }
        return res;
    }


    int mRows;
    int mCols;

    /**
     * 065-矩阵中的路径(BFS)
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        mRows = rows;
        mCols = cols;
        if (rows == 1 && cols == 1) {
            return matrix[0] == str[0];
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, i, j, 0, str)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[] matrix, int x, int y, int u, char[] str) {
        if (u == str.length) {
            return true;
        }
        int index = x * mCols + y;
        if (matrix[index] != str[u]) {
            return false;
        }
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        char t = matrix[index];
        matrix[index] = '*';
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a >= 0 && a < mRows && b >= 0 && b < mCols) {
                if (dfs(matrix, a, b, u + 1, str)) return true;
            }
        }
        matrix[index] = t;
        return false;
    }
}
