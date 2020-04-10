package learn.fibonacci;

/**
 * Created by liubin on 2020/4/10.
 */
public class Fibonacci {

    /**
     * 07-斐波拉契数列
     *
     * @param n
     * @return
     */
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
     * 008-跳台阶
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
     * 009-变态跳台阶
     * <p>
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
     * 变态跳台阶(动态规划解法)
     *
     * @param target
     * @return
     */
    public static int JumpFloorIIdp(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target < 2) {
            return 1;
        }

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= target; j++) {
                if (i < j) {
                    continue;
                }
                dp[i] = dp[i] + dp[i - j];
            }
        }
        return dp[target];
    }

}
