## 其他算法题目

### 002-替换空格

## 题目描述

请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

```java
/**
 * 002-替换空格
 *
 * @param str
 * @return
 */
public String replaceSpace(StringBuffer str) {
    if(str==null){
        return null;
    }
    StringBuilder newStr = new StringBuilder();
    for(int i=0;i<str.length();i++){
        if(str.charAt(i)==' '){
            newStr.append('%');
            newStr.append('2');
            newStr.append('0');
        }else{
            newStr.append(str.charAt(i));
        }
    }
    return newStr.toString();
}
```

### 013-调整数组顺序使奇数位于偶数前面

```java
/**
 * 013-调整数组顺序使奇数位于偶数前面
 * <p>
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
```

### 028-数组中出现次数超过一半的数字

## 题目描述

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

### 思路：

定义一个数字result初始值位array[0]代表当前遍历的数，定义times初始值为1代表当前数重复的数目，遍历数组，如果times为0则将result置为array[j],否则判断array[j]与result相等的话times++，如果不相等times- -, 最后遍历result出现的数目超过一半则返回result。

```java
/**
 * 028-数组中出现次数超过一半的数字
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
```

### 031-整数中1出现的次数（从1到n整数中1出现的次数）

## 题目描述

求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。

```java
/**
 * 031-整数中1出现的次数（从1到n整数中1出现的次数）
 * @param n
 * @return
 */
public int NumberOf1Between1AndN_Solution(int n) {
    if (n <= 1) {
        return n;
    }
    int left = 0, right = 0, current = 0;
    int factor = 1;
    int res = 0;
    while (n / factor != 0) {
        left = n / (factor * 10);
        right = n - (n / factor) * factor;
        current = (n / factor) % 10;
        res += left * factor;
        if (current == 1) {
            res += right + 1;
        } else if (current > 1) {
            res += factor;
        }
        factor *= 10;
    }
    return res;
}
```

### 032-把数组排成最小的数

```java
/**
 * 032-把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 *
 * @param numbers
 * @return
 */
public String PrintMinNumber(int[] numbers) {
    List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.<Integer>toList());
    Collections.sort(list, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = String.valueOf(o1);
            String s2 = String.valueOf(o2);
            return Integer.valueOf(s1 + s2) - Integer.valueOf(s2 + s1);
        }
    });
    StringBuilder res = new StringBuilder();
    for (Integer i : list) {
        res.append(String.valueOf(i));
    }
    return res.toString();
}
```

### 033-丑数

## 题目描述

把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

```java
/**
 * 033-丑数
 *
 * @param index
 * @return
 */
public int GetUglyNumber_Solution(int index) {
    if (index < 7)
        return index;

    int i = 0, j = 0, k = 0;
    int num = 1;
    List<Integer> list = new ArrayList<>();
    list.add(num);
    while (list.size() < index) {
        num = Math.min(list.get(i) * 2, Math.min(list.get(j) * 3, list.get(k) * 5));
        if (list.get(i) * 2 == num) i++;

        if (list.get(j) * 3 == num) j++;

        if (list.get(k) * 5 == num) k++;

        list.add(num);
    }
    return num;
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

### 041-和为S的连续正数序列(滑动窗口思想)

```java
/**
 * 041-和为s的连续正整数序列
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * @param sum
 * @return
 */
public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    int s = 1;
    for (int i = 1, j = 1; i <= sum; i++) {
        while (s < sum) {
            j++;
            s += j;
        }
        if (s == sum && j > i) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                list.add(k);
            }
            res.add(list);
        }
        s -= i;
    }
    return res;
}
```

### 042-和为S的两个数字(双指针思想)

```java
/**
 * 042-和为s的两个数字
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 * @param array
 * @param sum
 * @return
 */
public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
    ArrayList<Integer> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int product = Integer.MAX_VALUE;
    for (int i = 0; i < array.length; i++) {
        int temp = sum - array[i];
        if (list.contains(temp)) {
            if (temp * array[i] < product) {
                product = temp * array[i];
                res.clear();
                res.add(Math.min(temp, array[i]));
                res.add(Math.max(temp, array[i]));
            }
        } else {
            list.add(array[i]);
        }
    }
    return res;
}
```

### 043-左旋转字符串(矩阵翻转)

## 题目描述

汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！

```java
/**
 * 043-左旋转字符串(先反转字符串，然后把字符串从0～s-n反转，然后反转s-n+1～s)
 * @param str
 * @param n
 * @return
 */
public String LeftRotateString(String str,int n) {
    if(str == null || "".equals(str)){
        return "";
    }
    if(n >= str.length()){
        return str;
    }
    char[] arr = str.toCharArray();
    int s = arr.length - 1;
    reverse(arr, 0, s);
    reverse(arr, 0, s - n);
    reverse(arr, s - n + 1, s);
    return String.valueOf(arr);
}
```

### 046-孩子们的游戏-圆圈中最后剩下的数(约瑟夫环)

```java
/**
 * 046-孩子们的游戏-圆圈中最后剩下的数(约瑟夫环)
 *
 * @param n
 * @param m
 * @return
 */
public int LastRemaining_Solution(int n, int m) {
    if (n == 0) {
        return -1;
    }

    return dp(n, m);

}

public int dp(int n, int m) {
    if (n == 1) {
        return 0;
    }
    return (dp(n - 1, m) + m) % n;
}
```

### 051-构建乘积数组

## 题目描述

给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）

```java
/**
 * 051-构建乘积数组
 * @param A
 * @return
 */
public int[] multiply(int[] A) {
    if(A == null || A.length <=0){
        return null;
    }
    int n = A.length;
    int[] res = new int[n];
    int p = 1;
    for(int i = 0; i < n; i++){
        res[i] = p;
        p *= A[i];
    }
    p = 1;
    for(int i = n - 1; i >= 0; i--){
        res[i] *= p;
        p *= A[i];
    }
    return res;
}
```