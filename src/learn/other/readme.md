## 其他算法题目

### 029-最小的K个数

### 题目描述

输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

### 思路

思路1：

- 最小堆建立(需要o(n)时间复杂度)
- 取出前k个数(每次取需要用logn时间重建堆)。时间复杂度为o(n)+o(k*logn)

思路2:

- 用快排partition划分，一直划中第k个数 最差情况o(kn)

```java
/**
     * 从一个数组中获取k个最小值
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinNumbers(int[] arr, int k) {
        if (k >= arr.length) {
            throw new RuntimeException("k is too big");
        }

        if (k == arr.length - 1) {
            return arr;
        }
        int[] rangeArr = Arrays.copyOfRange(arr, 0, k);

        heapSort(rangeArr);

        for (int i = k; i < arr.length; i++) {
            if (rangeArr[k - 1] > arr[i]) {
                rangeArr[k - 1] = arr[i];
                heapSort(rangeArr);
            }
        }

        Utils.printIntArr(rangeArr);
        return rangeArr;
    }

    public static void heapSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            buildMaxHeap(arr, arr.length - 1 - i);
            swap(arr, 0, arr.length - 1 - i);
        }
    }

    public static void buildMaxHeap(int[] arr, int lastIndex) {
        int mid = (lastIndex - 1) / 2;
        for (int i = mid; i >= 0; i--) {
            int k = i;
            while (2 * k + 1 <= lastIndex) {
                int childIndex = 2 * k + 1;
                int rightChildIndex = childIndex + 1;
                if (rightChildIndex <= lastIndex && arr[childIndex] < arr[rightChildIndex]) {
                    childIndex = rightChildIndex;
                }
                if (arr[k] < arr[childIndex]) {
                    swap(arr, k, childIndex);
                    k = childIndex;
                } else {
                    break;
                }
            }
        }
    }
```

```java
public static int[] getMinNumbersWithQuickSort(int[] arr, int k) {
        if (arr == null || k >= arr.length) {
            throw new RuntimeException("k is too big");
        }
        if (k == arr.length - 1) {
            return arr;
        }
        int start = 0;
        int end = arr.length - 1;
        int index = partition(arr, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(arr, start, end);
            } else {
                start = index + 1;
                index = partition(arr, start, end);
            }
        }
        int[] outPut = new int[k];
        for (int i = 0; i < k; i++) {
            outPut[i] = arr[i];
        }
        Utils.printIntArr(outPut);
        return outPut;
    }

public static int partition(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 0 || start < 0 || end >= arr.length) {
            throw new RuntimeException("data is error");
        }
        int key = start;
        swap(arr, key, end);

        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                ++small;
                if (small != i) {
                    swap(arr, small, i);
                }
            }
        }
        ++small;
        swap(arr, small, end);
        Utils.printIntArr(arr);
        return small;
    }
```

### 034-第一个只出现一次的字符

### 题目描述

在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.

### 解题思路

主要还是hash，利用每个字母的ASCII码作hash来作为数组的index。首先用一个58长度的数组来存储每个字母出现的次数，为什么是58呢，主要是由于A-Z对应的ASCII码为65-90，a-z对应的ASCII码值为97-122，而每个字母的index=int(word)-65，比如g=103-65=38，而数组中具体记录的内容是该字母出现的次数，最终遍历一遍字符串，找出第一个数组内容为1的字母就可以了，时间复杂度为O(n)

```java
public int FirstNotRepeatingChar(String str) {
    
    int[] words = new int[58];
    for(int i = 0;i<str.length();i++){
        words[((int)str.charAt(i))-65] += 1;
    }
    for(int i=0;i<str.length();i++){
        if(words[((int)str.charAt(i))-65]==1)
            return i;
    }
    return -1;
    }
```

### 07-斐波那契数列

### 题目描述

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。 n<=39

### 思路

$$f(n)=f(n-1)+f(n-2)​$$

```java
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
  //递归解法
//        return fibonacci(n - 1) + fibonacci(n - 2);
    }
```

###  008-跳台阶

### 题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

### 思路

假设对于第n级台阶，总共有f(n)种跳法.

那么f(n) = f(n-1) + f(n-2)，其中f(1)=1,f(2)=2

```java
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
```

