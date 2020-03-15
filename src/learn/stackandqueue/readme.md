## 栈和队列题目

- 005-用两个栈实现队列

  ### 题目描述

  用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

  ### 思路

  一个栈用来存储 pop时弹出stack2，stack2为空，pop出stack1存储在stack2中

  ```java
  Stack<Integer> stack1 = new Stack<Integer>();
      Stack<Integer> stack2 = new Stack<Integer>();
  
      public void push(int node) {
          stack1.push(node);
      }
  
      public int pop() {
          if(stack1.isEmpty() && stack2.isEmpty()){
              return 0;
          }
          if(stack2.isEmpty()){
              while(!stack1.isEmpty()){
                  stack2.push(stack1.pop());
              }
          }
          return stack2.pop();
      }
  ```

  

- 020-包含min函数的栈

  ### 题目描述

  定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

  ### 思路

  用辅助栈存储当前data的最小值，辅助栈头即为min值。

  ```java
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
       * @return
       */
      public int min() {
          return min;
      }
  ```

   

- 021-栈的压入、弹出序列

  ### 题目描述

  输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

  ### 思路

  遍历压入栈，存储于栈中，遍历过程中，如果栈顶是出栈结点，推出值。

  - 最终栈空则弹出序列有效
  - 栈不空则弹出序列无效

   

  ```java
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
  ```

  

- 044-翻转单词顺序列(栈)

  ### 题目描述

  牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？

  ### 思路

  考察翻转，可以通过空格隔开，然后用栈存储，最后弹出。

   

  ```java
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
  ```

  

- 064-滑动窗口的最大值(双端队列)

  ### 题目描述

  给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：

  - {[2,3,4],2,6,2,5,1}
  - {2,[3,4,2],6,2,5,1}
  - {2,3,[4,2,6],2,5,1}
  - {2,3,4,[2,6,2],5,1}
  - {2,3,4,2,[6,2,5],1}
  - {2,3,4,2,6,[2,5,1]}

  ### 思路

  滑动窗口用队列保存，当遇到以下情况做相应处理：

  - 窗口第一个槽始终是最大值
  - 窗口第一个槽过期要丢弃
  - 窗口从尾比到头，丢弃较小的数

   

  ```java
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
  ```

  

