### 链表类题目

- 003-从尾到头打印链表

  #### 题目描述

  输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

  #### 思路

  用栈存放，再输出

  ```java
  public static ArrayList<Integer> printListFromTailToHead(LinkNode listNode) {
          Stack<Integer> stack = new Stack();
          while (listNode != null) {
              stack.push(listNode.value);
              listNode = listNode.next;
          }
          ArrayList<Integer> list = new ArrayList<>();
          int length = stack.size();
          for (int i = 0; i < length; i++) {
              list.add(stack.pop());
          }
          return list;
      }
  ```

  

- 014-链表中倒数第k个结点

  ### 题目描述

  输入一个链表，输出该链表中倒数第k个结点。

  ### 思路

  用快慢指针，快指针比慢指针快k步，到尾结点了慢指针就是倒数第k个结点。

  ```java
      public static LinkNode FindKthToTail(LinkNode head, int k) {
          if (head == null || head.next == null) {
              return head;
          }
  
          LinkNode first = head;
          LinkNode second = head;
  
          while (first != null && k > 0) {
              first = first.next;
              k--;
          }
          if (first == null && k > 0) {
              return null;
          }
          while (first != null) {
              first = first.next;
              second = second.next;
          }
          return second;
      }
  
  ```

  

- 015-反转链表

  ### 题目描述

  输入一个链表，反转链表后，输出新链表的表头。

  ### 思路

  假设翻转1->2->3->4->5，步骤如下：

  ```
  head->4->3->2->1->5
                 p  tp
  ```

  - 1.我们将p的next指向tp的next
  - 2.将tp的next指向head的next
  - 3.将head的next指向tp

  ```java
  public static LinkNode reverseLink2(LinkNode head) {
          if (head == null || head.next == null) {
              return head;
          }
  
          LinkNode reHead = null;
  
          while (head != null) {
              LinkNode temp = head.next;
              head.next = reHead;
              reHead = head;
              head = temp;
          }
  
          return reHead;
      }
  
  ```

  

- 016-合并两个或k个有序链表

  ### 题目描述

  输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

  ### 思路

  新开个链表，按大小存储即可

   

  ```java
  public static LinkNode Merge(LinkNode list1, LinkNode list2) {
          if (list1 == null) {
              return list2;
          }
          if (list2 == null) {
              return list1;
          }
  
          /**
           * 递归实现
           */
          /*if (list1.value < list2.value) {
              list1.next = Merge(list1.next, list2);
              return list1;
          } else {
              list2.next = Merge(list1, list2.next);
              return list2;
          }*/
  
          LinkNode merge;
  
          if (list1.value < list2.value) {
              merge = list1;
              list1 = list1.next;
          } else {
              merge = list2;
              list2 = list2.next;
          }
  
          LinkNode temp = merge;
  
          while (list1 != null && list2 != null) {
              if (list1.value < list2.value) {
                  temp.next = list1;
                  list1 = list1.next;
              } else {
                  temp.next = list2;
                  list2 = list2.next;
              }
              temp = temp.next;
          }
  
          while (list1 != null) {
              temp.next = list1;
              list1 = list1.next;
              temp = temp.next;
          }
          while (list2 != null) {
              temp.next = list2;
              list2 = list2.next;
              temp = temp.next;
          }
          return merge;
      }
  ```

  

- 025-复杂链表的复制

- 036-两个链表的第一个公共结点

- 055-链表中环的入口结点

- 056-删除链表中重复的结点