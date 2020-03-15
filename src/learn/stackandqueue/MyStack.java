package learn.stackandqueue;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by liubin on 2017/5/30.
 */
public class MyStack {

    private static final int DEFAULT_ARR_LENGTH = 4;
    private static final int MAX_LENGTH = Integer.MAX_VALUE - 8;

    Integer[] array;
    private int mGrowLength;
    private int mSize;

    public MyStack() {
        this(DEFAULT_ARR_LENGTH);
    }

    public MyStack(int length) {
        this(DEFAULT_ARR_LENGTH, 0);
    }

    public MyStack(int length, int growLength) {
        array = new Integer[length];
        mGrowLength = growLength;
    }

    public void push(Integer t) {
        if (array.length <= mSize) {
            grow(mSize + 1);
        }
        array[mSize++] = t;

        //020-包含min函数的栈
        if (t <= min) {
            minStack.push(t);
            min = minStack.peek();
        } else {
            minStack.push(min);
        }
    }

    private void grow(int minLength) {
        int oldLength = array.length;
        int newLength = oldLength + (mGrowLength > 0 ? mGrowLength : oldLength);
        if (newLength - minLength < 0) {
            newLength = minLength;
        }
        if (newLength > MAX_LENGTH) {
            newLength = minLength > MAX_LENGTH ? Integer.MAX_VALUE : MAX_LENGTH;
        }

        array = Arrays.copyOf(array, newLength);
    }

    public Integer pop() {
        Integer t = array[mSize - 1];
        mSize--;
        array[mSize] = null;
        minStack.pop();
        return t;
    }

    private int min = Integer.MAX_VALUE;
    private Stack<Integer> minStack = new Stack<Integer>();

    /**
     * 020-包含min函数的栈
     *
     * @return
     */
    public int min() {
        return min;
    }

    public boolean empty() {
        return mSize == 0;
    }


    public static void main(String[] args) {
//        MyStack stack = new MyStack();
        Stack<Integer> stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        reverse(stack);
        while (!stack.empty()) {
            System.out.print(stack.pop().toString() + " ");
        }

//        System.out.println();
//        boolean isPopOrder = IsPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
//        System.out.print("isPopOrder == " + isPopOrder);
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * 这个函数就是删除栈底元素并返回这个元素
     *
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 005-用两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 005-用两个栈实现队列 push
     */
    public void push(int node) {
        stack1.push(node);
    }

    /**
     * 005-用两个栈实现队列 pop
     */
    public int realpop() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return 0;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


    /**
     * 021-栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。\
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     *
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || pushA.length < 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (j < popA.length && !stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 044-翻转单词顺序列(栈)
     */
    public String ReverseSentence(String str) {
        if (str.trim().equals("") && str.length() > 0) {
            return str;
        }
        Stack<String> reverse = new Stack<>();
        String string = str.trim();
        String[] strings = string.split(" ");
        for (int i = 0; i < strings.length; i++) {
            reverse.push(strings[i]);
        }
        string = reverse.pop();
        while (!reverse.isEmpty()) {
            string = string + " " + reverse.pop();
        }
        return string;
    }


    /**
     * 064-滑动窗口的最大值
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        if (num == null || num.length == 0 || size <= 0 || num.length < size) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        //双端队列，用来记录每个窗口的最大值下标
        LinkedList<Integer> qmax = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < num.length; i++) {
            while (!qmax.isEmpty() && num[qmax.peekLast()] < num[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            //判断队首元素是否过期
            if (qmax.peekFirst() == i - size) {
                qmax.pollFirst();
            }
            //向result列表中加入元素
            if (i >= size - 1) {
                result.add(num[qmax.peekFirst()]);
            }
        }
        return result;
    }
}
