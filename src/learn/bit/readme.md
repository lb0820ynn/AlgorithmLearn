## 位运算相关题目

### 011-二进制中1的个数

## 题目描述

输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。

```java
/**
 * 011-二进制中1的个数
 *
 * @param n
 * @return
 */
public int NumberOf1(int n) {
    int count = 0;

    while (n != 0) {
        count += (n & 1);
        n >>>= 1;
    }
    return count;
}
```

### 012-数值的整数次方

## 题目描述

给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

保证base和exponent不同时为0

```java
/**
 * 012-数值的整数次方
 *
 * @param base
 * @param n
 * @return
 */
public double Power(double base, int n) {
    double res = 1;
    int exponent;
    if (n > 0) {
        exponent = n;
    } else if (n < 0) {
        if (base == 0)
            throw new RuntimeException("分母不能为0");
        exponent = -n;
    } else {// n==0
        return 1;// 0的0次方
    }
    for (int i = 0; i < exponent; i++) {
        res *= base;
    }

    return n >= 0 ? res : (1 / res);
}
```

### 040-数组中只出现一次的数字

## 题目描述

一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。

```java
/**
 * 040-数组中只出现一次的两个数字
 *
 * @param array
 * @param num1
 * @param num2
 */
public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
    int sum = 0;
    for (int i : array) {
        sum ^= i;
    }
    int k = 0;
    while (!((sum >> k & 1) == 1)) {
        k++;
    }
    for (int j : array) {
        if ((j >> k & 1) == 1) {
            num1[0] ^= j;
        }
    }
    num2[0] = num1[0] ^ sum;
}
```

### 在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。

```java
/**
 * 在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。
 * 请找出那个只出现一次的数字。
 * 你可以假设满足条件的数字一定存在。
 * 思考题：
 * 如果要求只使用 O(n) 的时间和额外 O(1) 的空间，该怎么做呢？
 *
 * @param nums
 * @return
 */
public int findNumberAppearingOnce(int[] nums) {
    int ones = 0, twos = 0;
    for (int x : nums) {
        ones = (ones ^ x) & ~twos;
        twos = (twos ^ x) & ~ones;
    }
    System.out.println("ones == " + ones + "  twos == " + twos);
    return ones;
}
```

### 在一个数组中除了**两个**个数字只出现一次之外，其他数字都出现了三次。

```java
/**
 * 在一个数组中除了两个个数字只出现一次之外，其他数字都出现了三次。
 * 请找出两个只出现一次的数字。
 * 你可以假设满足条件的数字一定存在
 *
 * @param nums
 */
public void findNumberAppearingOnce2(int[] nums) {
    int ones = 0, twos = 0;
    for (int i : nums) {
        ones = (ones ^ i) & ~twos;
        twos = (twos ^ i) & ~ones;
    }
    int k = 0;
    while (!((ones >> k & 1) == 1)) {
        k++;
    }
    ones = 0;
    twos = 0;
    int ones2 = 0, twos2 = 0;
    for (int j : nums) {
        if ((j >> k & 1) == 1) {
            ones = (ones ^ j) & ~twos;
            twos = (twos ^ j) & ~ones;
        } else {
            ones2 = (ones2 ^ j) & ~twos2;
            twos2 = (twos2 ^ j) & ~ones2;
        }
    }
    System.out.println("res1 == " + ones + "  res2 == " + ones2);
}
```