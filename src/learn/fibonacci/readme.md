### 007-斐波拉契数列
### 题目描述

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。 n<=39

### 思路

$$f(n)=f(n-1)+f(n-2)$$

```java
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
```

### 008-跳台阶
### 题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

### 思路

假设对于第n级台阶，总共有f(n)种跳法.

那么f(n) = f(n-1) + f(n-2)，其中f(1)=1,f(2)=2

```java
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
```

### 009-变态跳台阶
### 题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

### 思路

因为n级台阶，第一步有n种跳法：跳1级、跳2级、到跳n级

 跳1级，剩下n-1级，则剩下跳法是f(n-1)

 跳2级，剩下n-2级，则剩下跳法是f(n-2)

 所以f(n)=f(n-1)+f(n-2)+...+f(1)

 因为f(n-1)=f(n-2)+f(n-3)+...+f(1)

  所以f(n)=2*f(n-1)

```java
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
```