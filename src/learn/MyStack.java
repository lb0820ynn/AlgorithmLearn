package learn;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by liubin on 2017/5/30.
 */
public class MyStack<T> {

    private static final int DEFAULT_ARR_LENGTH = 4;
    private static final int MAX_LENGTH = Integer.MAX_VALUE - 8;

    Object[] array;
    private int mGrowLength;
    private int mSize;

    public MyStack() {
        this(DEFAULT_ARR_LENGTH);
    }

    public MyStack(int length) {
        this(DEFAULT_ARR_LENGTH, 0);
    }

    public MyStack(int length, int growLength) {
        array = new Object[length];
        mGrowLength = growLength;
    }

    public void push(T t) {
        if (array.length <= mSize) {
            grow(mSize + 1);
        }
        array[mSize++] = t;
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

    public T pop() {
        T t = (T) array[mSize - 1];
        mSize--;
        array[mSize] = null;
        return t;
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
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。\
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
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
}
