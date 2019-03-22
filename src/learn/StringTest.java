package learn;

import java.util.*;

/**
 * Created by liubin on 2017/6/9.
 */
public class StringTest {

    public  String a = "ssss";



    public static void main(String[] args) {
//        int value = Character.digit('/', 10);
//        int temp = '9' - '0';
//        System.out.print(temp + " ");
//        System.out.print((int) ('a'));

//        int i = parseInt("2378734743");

//        System.out.print(i);

//        System.out.println();
//
        System.out.print(getFirstNoRepeatChar("sjsjghjhajsgjaksdghaajkghjz"));

        System.out.println();

        System.out.println();
//
//        System.out.print(replaceSpace2(new StringBuffer(" helloworld")));


        int num = 0b11111111;
        String s = Integer.toBinaryString(num);
        //负数转十进制
//        BigInteger bi = new BigInteger(num);
//        bi.toString(2);


//        System.out.println(0xffffffff);

        byte b = -1;
        byte a = (byte) 0b11110000;

        System.out.println(2 << 8);
        System.out.println(a * 1.0f);
//        System.out.println(Integer.signum(a));
//        System.out.println(Integer.parseInt(Integer.toBinaryString(-5)));


//        printComplementCode(-10);

        System.out.println(NumberOf1(4));


        System.out.println();

        ArrayList<String> abc = Permutation("abcb");
        Utils.printList(abc);

    }

    public  void test(StringTest s) {
        s = new StringTest();
        s.a = "bbb";
    }

    public static int NumberOf1(int n) {
        int count = 0;

        while (n != 0) {
            ++count;
            n = n & n - 1;
        }
        return count;
    }


    public static void printComplementCode(int a) {
        for (int i = 0; i < 32; i++) {
            // 0x80000000 是一个首位为1，其余位数为0的整数
            int t = (a & 0x80000000 >>> i) >>> (31 - i);
            System.out.print(t);
        }
        System.out.println();
    }

    public static int parseInt(String str) {
        if (str == null)
            throw new NumberFormatException("str is null");

        boolean negative = false;

        int i = 0;
        int len = str.length();
        int result = 0;
        int max = Integer.MAX_VALUE;
        int limit;
        if (len > 0) {
            char firstChar = str.charAt(0);
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                } else if (firstChar != '+') {
                    throw new NumberFormatException();
                }
                i++;
            }

            limit = max / 10;
            while (i < len) {
                char item = str.charAt(i);
                if (!isNumber(item)) {
                    throw new NumberFormatException("str is not number");
                }
                if (result > limit) {
                    throw new NumberFormatException("value is too big or small");
                }
                result *= 10;

                if (result > max) {
                    throw new NumberFormatException("value is too big or small");
                }
                result += (int) (item - '0');
                i++;
            }
        }

        return negative ? -result : result;
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


    public static Character getFirstNoRepeatChar(String str) {
        if (str == null) {
            throw new RuntimeException("str is null");
        }
        char[] arr = str.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char item : arr) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }

        Set<Character> characters = map.keySet();
        for (char key : characters) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return null;
    }

    public static String replaceSpace(String str) {
        if (str == null) {
            throw new RuntimeException("str is null");
        }

        int count = getSpaceCount(str);

        int newLength = str.length() + count * 2;
        int length = str.length();

        char[] newArr = new char[newLength];

        System.arraycopy(str.toCharArray(), 0, newArr, 0, length);
        int index1 = length - 1;
        int index2 = newLength - 1;

        while (index1 >= 0 && index1 != index2) {

            if (newArr[index1] == ' ') {
                newArr[index2--] = '0';
                newArr[index2--] = '2';
                newArr[index2--] = '%';
            } else {
                newArr[index2--] = newArr[index1];
            }

            index1--;

        }

        return String.copyValueOf(newArr);

    }

    private static int getSpaceCount(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }

        return count;
    }

    public static String replaceSpace2(StringBuffer str) {
        int count = getSpaceCount(str);
        int len = str.length();
        int newLen = len + count * 2;
        char[] strArr = new char[newLen];

        System.arraycopy(str.toString().toCharArray(), 0, strArr, 0, len);
        int index = len - 1, indexNew = newLen - 1;
        while (index >= 0 && index != indexNew) {
            if (strArr[index] != ' ') {
                strArr[indexNew--] = strArr[index--];
            } else {
                strArr[indexNew--] = '0';
                strArr[indexNew--] = '2';
                strArr[indexNew--] = '%';
                index--;
            }
        }
        return String.copyValueOf(strArr);
    }

    public static int getSpaceCount(StringBuffer str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }


    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str != null && str.length() > 0) {
            goPermutation(str.toCharArray(), 0, list);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
     * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * 输入描述:
     * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     *
     * @param chars
     * @param i
     * @param list
     */
    private static void goPermutation(char[] chars, int i, ArrayList<String> list) {
        if (i == chars.length - 1) {
            String str = String.valueOf(chars);
            if (!list.contains(str)) {
                list.add(String.valueOf(chars));
            }
        } else {
            for (int j = i; j < chars.length; j++) {
                swap(chars, i, j);
                goPermutation(chars, i + 1, list);
                swap(chars, j, i);
            }

            //递归的思想与栈的入栈和出栈是一样的,某一个状态遇到return结束了之后，会回到被调用的地方继续执行

            //1.第一次进到这里是ch=['a','b','c'],list=[],i=0，我称为 状态A ，即初始状态
            //那么j=0，swap(ch,0,0)，就是['a','b','c']，进入递归，自己调自己，只是i为1，交换(0,0)位置之后的状态我称为 状态B
            //i不等于2，来到这里，j=1，执行第一个swap(ch,1,1)，这个状态我称为 状态C1 ,再进入fun函数，此时标记为T1，i为2，那么这时就进入上一个if，将"abc"放进list中
            /////////////-------》此时结果集为["abc"]

            //2.执行完list.add之后，遇到return，回退到T1处，接下来执行第二个swap(ch,1,1)，状态C1又恢复为状态B
            //恢复完之后，继续执行for循环，此时j=2,那么swap(ch,1,2),得到"acb"，这个状态我称为C2,然后执行fun，此时标记为T2,发现i+1=2,所以也被添加进结果集，此时return回退到T2处往下执行
            /////////////-------》此时结果集为["abc","acb"]
            //然后执行第二个swap(ch,1,2)，状态C2回归状态B,然后状态B的for循环退出回到状态A

            //             a|b|c(状态A)
            //               |
            //               |swap(0,0)
            //               |
            //             a|b|c(状态B)
            //             /  \
            //   swap(1,1)/    \swap(1,2)  (状态C1和状态C2)
            //           /      \
            //         a|b|c   a|c|b

            //3.回到状态A之后，继续for循环，j=1,即swap(ch,0,1)，即"bac",这个状态可以再次叫做状态A,下面的步骤同上
            /////////////-------》此时结果集为["abc","acb","bac","bca"]

            //             a|b|c(状态A)
            //               |
            //               |swap(0,1)
            //               |
            //             b|a|c(状态B)
            //             /  \
            //   swap(1,1)/    \swap(1,2)  (状态C1和状态C2)
            //           /      \
            //         b|a|c   b|c|a

            //4.再继续for循环，j=2,即swap(ch,0,2)，即"cab",这个状态可以再次叫做状态A，下面的步骤同上
            /////////////-------》此时结果集为["abc","acb","bac","bca","cab","cba"]

            //             a|b|c(状态A)
            //               |
            //               |swap(0,2)
            //               |
            //             c|b|a(状态B)
            //             /  \
            //   swap(1,1)/    \swap(1,2)  (状态C1和状态C2)
            //           /      \
            //         c|b|a   c|a|b

            //5.最后退出for循环，结束。
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


}