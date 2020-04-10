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

  ### 题目描述

  输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

  ### 思路

  方案1：

  - 第一步：遍历老链表，构建正常的链表，用一个map记录p到new_p
  - 第二步：新老链表同步next移动，对比记录random指针。

  p 1->2->3->4 map | | | | new_p 1->2->3->4

  需要借助O(n)的空间，时间复杂度为o(n)

  方案2：

  不需要借助O(n)的空间，时间复杂度为o(n)

  老新链表交叉存储，奇数位置为老链表，偶数位置新链表复制前一个位置。

  新链表random即为旧链表random的后一个位置。

  p1->p1'->p2->p2'->...->pn->pn'

  方案2代码如下：

  ```java
  public static RandomListNode Clone(RandomListNode pHead) {
          if (pHead == null) {
              return null;
          }
          RandomListNode currentNode = pHead;
  
          while (currentNode != null) {
              RandomListNode cloneNode = new RandomListNode(currentNode.label);
              cloneNode.next = currentNode.next;
              currentNode.next = cloneNode;
              currentNode = cloneNode.next;
          }
          currentNode = pHead;
          while (currentNode != null) {
              currentNode.next.random = currentNode.random == null ? null : currentNode.random.next;
              currentNode = currentNode.next.next;
          }
          currentNode = pHead;
          RandomListNode cloneNode = pHead.next;
          while (currentNode != null) {
              RandomListNode temp = currentNode.next;
              currentNode.next = temp.next;
              temp.next = temp.next == null ? null : temp.next.next;
              currentNode = currentNode.next;
          }
  
          return cloneNode;
      }
  ```

  

- 036-两个链表的第一个公共结点

  ### 题目描述

  输入两个链表，找出它们的第一个公共结点。

  ### 思路

  ![a](https://s1.ax1x.com/2020/04/10/GTC56O.png)

  p1和p2两个链表如果存在公共结点,p1到公共结点的距离为a,公共结点到尾部的距离为b,p2到公共结点的距离为c。

  则p1向后走到尾部把next置为p2的头节点继续向后走到公共结点,所走的距离为a+b+c，而p2走到尾部后把next置为p1的头节点继续走到公共结点，所走的距离为c+b+a，可以发现路程相同，所以如果两个结点同时出发一定会在公共结点相遇，如果两个链表没有公共结点则两个结点走到最后都会为null。

  

```java
public LinkNode FindFirstCommonNode(LinkNode pHead1, LinkNode pHead2) {
        LinkNode p = pHead1;
        LinkNode q = pHead2;
        while (p != q) {
            p = p != null ? p.next : pHead2;
            q = q != null ? q.next : pHead1;
        }
        return p;
    }
```


- 055-链表中环的入口结点

  ### 题目描述

  给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

  ### **思路：** 

  ![img](https://s1.ax1x.com/2020/04/10/GTSrOH.md.png)

  ​      设置快慢指针，都从链表头出发，快指针每次**走两步**，慢指针一次**走一步**，假如有环，一定相遇于环中某点(结论1)。接着让两个指针分别从相遇点和链表头出发，两者都改为每次**走一步**，最终相遇于环入口(结论2)。以下是两个结论证明： 

  ###   两个结论： 

    **1、设置快慢指针，假如有环，他们最后一定相遇。**  

    **2、两个指针分别从链表头和相遇点继续出发，每次走一步，最后一定相遇与环入口。**


    **证明结论1**：设置快慢指针fast和low，fast每次走两步，low每次走一步。假如有环，两者一定会相遇（因为low一旦进环，可看作fast在后面追赶low的过程，每次两者都接近一步，最后一定能追上）。 

     **证明结论2：**


    设： 

    链表头到环入口长度为--**a**  

    环入口到相遇点长度为--**b**  

    相遇点到环入口长度为--**c**  

    则：相遇时 

    **快指针路程=a+(b+c)k+b** ，k>=1  其中b+c为环的长度，k为绕环的圈数（k>=1,即最少一圈，不能是0圈，不然和慢指针走的一样长，矛盾）。 

     **慢指针路程=a+b**  

    快指针走的路程是慢指针的两倍，所以： 

     **（a+b）\*2=a+(b+c)k+b**  

    化简可得： 

    **a=(k-1)(b+c)+c**  这个式子的意思是：  **链表头到环入口的距离=相遇点到环入口的距离+（k-1）圈环长度**。其中k>=1,所以**k-1>=0**圈。所以两个指针分别从链表头和相遇点出发，最后一定相遇于环入口。

  

  ```java
  public LinkNode EntryNodeOfLoop(LinkNode pHead) {
          LinkNode fast = pHead;
          LinkNode low = pHead;
          while (fast != null && fast.next != null) {
              fast = fast.next.next;
              low = low.next;
              if (fast == low)
                  break;
          }
          if (fast == null || fast.next == null)
              return null;
          low = pHead;
          while (fast != low) {
              fast = fast.next;
              low = low.next;
          }
          return low;
      }
  ```

  

- 056-删除链表中重复的结点

  ### 题目描述

  在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

  非递归的代码：

  \1. 首先添加一个头节点，以方便碰到第一个，第二个节点就相同的情况

  2.设置 pre ，last 指针， pre指针指向当前确定不重复的那个节点，而last指针相当于工作指针，一直往后面搜索。

  ```java
  public static LinkNode deleteDuplication(LinkNode pHead) {
          if (pHead == null || pHead.next == null) {
              return pHead;
          }
          LinkNode head = new LinkNode(0);
          head.next = pHead;
          LinkNode pre = head;
          LinkNode fast = head.next;
          while (fast != null) {
              if (fast.next != null && fast.value == fast.next.value) {
                  while (fast.next != null && fast.value == fast.next.value) {
                      fast = fast.next;
                  }
                  pre.next = fast.next;
              } else {
                  pre = pre.next;
              }
              fast = fast.next;
          }
          return head.next;
      }
  ```

  