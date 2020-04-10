package learn.other;

import learn.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liubin on 2020/3/15.
 */
public class OtherAll {

    /**
     * 002-替换空格
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            } else {
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }

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

    /**
     * 028-数组中出现次数超过一半的数字
     * 数组求众数
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

    /**
     * 031-整数中1出现的次数（从1到n整数中1出现的次数）
     *
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

    /**
     * 043-左旋转字符串(先反转字符串，然后把字符串从0～s-n反转，然后反转s-n+1～s)
     *
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str, int n) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (n >= str.length()) {
            return str;
        }
        char[] arr = str.toCharArray();
        int s = arr.length - 1;
        reverse(arr, 0, s);
        reverse(arr, 0, s - n);
        reverse(arr, s - n + 1, s);
        return String.valueOf(arr);
    }

    public void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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

    /**
     * 050-找出数组中重复的数字
     * <p>
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        duplication[0] = -1;
        for (int i = 0; i < length; i++) {
            while (i != numbers[i] && numbers[numbers[i]] != numbers[i]) {
                swap(numbers, i, numbers[i]);
            }
            if (i != numbers[i] && numbers[numbers[i]] == numbers[i]) {
                duplication[0] = numbers[i];
                break;
            }
        }
        return duplication[0] != -1;

    }

    public void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    /**
     * 剪绳子
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * @param n
     * @return
     */
    public int cutRope(int n) {
        if (n <= 3) return (n - 1);
        int res = 1;
        if (n % 3 == 1) {
            res *= 4;
            n -= 4;
        }
        if (n % 3 == 2) {
            res *= 2;
            n -= 2;
        }
        while (n > 0) {
            res *= 3;
            n -= 3;
        }
        return res;
    }

    public static void main(String[] args) {
        OtherAll o = new OtherAll();

//        int[] a = new int[]{1, 1, 1, 2, 2, 2, 4, 4, 4, 9, 3};
//
//        o.findNumberAppearingOnce2(a);

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        ArrayList<ArrayList<Integer>> subSet = o.getSubSet(list);
//        for (ArrayList<Integer> item : subSet) {
//            Utils.printList(item);
//            System.out.println();
//        }

        o.getPrimes(10000);
        o.getPrimes2(10000);
        o.getPrimes3(10000);

    }

    public static void printQueue(PriorityQueue<Integer> queue) {
        for (int i : queue) {
            System.out.print(i + ",");
            System.out.println();
        }
    }


    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    /**
     * 数据流的中位数-插入
     *
     * @param num
     */
    public void Insert(Integer num) {
        minHeap.offer(num);
        if (maxHeap.size() > 0 && minHeap.peek() > maxHeap.peek()) {
            Integer min = minHeap.poll();
            Integer max = maxHeap.poll();
            minHeap.add(max);
            maxHeap.add(min);
        }
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    /**
     * 获取数据流的中位数
     *
     * @return
     */
    public Double GetMedian() {
        if (minHeap.size() <= 0 && maxHeap.size() <= 0) {
            return 0d;
        }
        if (((minHeap.size() + maxHeap.size()) & 1) > 0) {
            return Double.valueOf(minHeap.peek());
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }

    Map<Character, Integer> map = new HashMap<>();
    Queue<Character> q = new LinkedList<>();

    //Insert one char from stringstream

    /**
     * 字符流中第一个不重复的字符
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
     * 第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     *
     * @param ch
     */
    public void Insert(char ch) {
        int count = map.containsKey(ch) ? map.get(ch) : 0;
        count++;
        map.put(ch, count);
        if (count > 1) {
            while (q.size() > 0 && map.get(q.peek()) > 1) {
                q.poll();
            }
        } else {
            q.offer(ch);
        }

    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        if (q.isEmpty()) {
            return '#';
        }
        return q.peek();
    }


    /**
     * 骰子的点数
     * 将一个骰子投掷n次，获得的总点数为s，s的可能范围为n~6n。
     * <p>
     * 掷出某一点数，可能有多种掷法，例如投掷2次，掷出3点，共有[1,2],[2,1]两种掷法。
     * <p>
     * 请求出投掷n次，掷出n~6n点分别有多少种掷法。
     *
     * @param n
     * @return
     */
    public int[] numberOfDice(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6 * i; j++) {
                for (int k = 1; k <= Math.min(j, 6); k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        int[] res = new int[6 * n - n + 1];
        for (int i = n; i <= 6 * n; i++) {
            res[i - n] = dp[n][i];
        }

        return res;
    }

    /**
     * 扑克牌顺子
     *
     * @param numbers
     * @return
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return false;
        }
        Arrays.sort(numbers);
        int k = 0;
        while (numbers[k] == 0) {
            k++;
        }
        for (int i = k + 1; i < numbers.length; i++) {
            if (numbers[i] == numbers[i - 1]) {
                return false;
            }
        }
        return numbers[numbers.length - 1] - numbers[k] <= 4;
    }

    /**
     * 不用加减乘除做加法
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int sum = num1 ^ num2;
            int carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }


    /**
     * 求集合的所有子集
     *
     * @param list
     * @return
     */
    public ArrayList<ArrayList<Integer>> getSubSet(ArrayList<Integer> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int n = 1 << list.size();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> sub = new ArrayList<>();
            int index = i;
            for (int j = 0; j < list.size(); j++) {
                if ((index & 1) == 1) {
                    sub.add(list.get(j));
                }
                index = index >> 1;
            }
            res.add(sub);
        }
        return res;
    }


    /**
     * 求质数
     * 线性筛法O(n)
     * //算法核心：x仅会被其最小质因子筛去
     *
     * @param n
     */
    public void getPrimes(int n) {
        boolean[] b = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        //外层从2~n迭代，因为这毕竟算的是1~n中质数的个数，而不是某个数是不是质数的判定
        for (int i = 2; i <= n; i++) {
            if (!b[i]) {
                list.add(i);
            }
            for (int j = 0; list.get(j) <= n / i; j++) {//primes[j]<=n/i:变形一下得到——primes[j]*i<=n,把大于n的合数都筛了没啥意义了
                cnt++;
                b[list.get(j) * i] = true;
                //1)当i%primes[j]!=0时,说明此时遍历到的primes[j]不是i的质因子，那么只可能是此时的primes[j]<i的
                //最小质因子,所以primes[j]*i的最小质因子就是primes[j];
                //2)当有i%primes[j]==0时,说明i的最小质因子是primes[j],因此primes[j]*i的最小质因子也就应该是
                //prime[j]，之后接着用st[primes[j+1]*i]=true去筛合数时，就不是用最小质因子去更新了,因为i有最小
                //质因子primes[j]<primes[j+1],此时的primes[j+1]不是primes[j+1]*i的最小质因子，此时就应该
                //退出循环，避免之后重复进行筛选。
                if ((i % list.get(j)) == 0) {
                    break;
                }
            }
        }
        System.out.println("cnt == " + cnt);
        Utils.printList(list);
    }

    /**
     * 求质数
     * 普通筛法
     *
     * @param n
     */
    public void getPrimes2(int n) {
        boolean[] b = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!b[i]) {
                list.add(i);
            }
            for (int j = 0; j <= n; j += i) {
                cnt++;
                b[j] = true;
            }
        }
        System.out.println();
        System.out.println("cnt == " + cnt);
        Utils.printList(list);
    }

    /**
     * 求质数
     * 诶氏筛法
     *
     * @param n
     */
    public void getPrimes3(int n) {
        boolean[] b = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!b[i]) {
                list.add(i);
                for (int j = i * i; j <= n; j += i) {
                    cnt++;
                    b[j] = true;
                }
            }
        }
        System.out.println();
        System.out.println("cnt == " + cnt);
        Utils.printList(list);
    }

}
