package learn.linkedlist;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by liubin on 2017/5/31.
 */
public class LinkNode {
    public int value;
    public LinkNode next;

    public LinkNode(int value) {
        this(value, null);
    }

    public LinkNode(int value, LinkNode next) {
        this.value = value;
        this.next = next;
    }

    public static void printLink(LinkNode head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    /**
     * 逆序打印链表 递归
     *
     * @param head
     */
    public static void reversePrintLink(LinkNode head) {
        if (head == null) {
            return;
        }

        reversePrintLink(head.next);
        System.out.print(head.value + " ");
    }

    /**
     * 003-从尾到头打印链表
     *
     * @param listNode
     * @return
     */
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

    /**
     * 014-链表中倒数第k个结点
     *
     * @param head
     * @param k
     */
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

    /**
     * 015-反转单链表 递归
     *
     * @param head
     * @return
     */
    public static LinkNode reverseLink(LinkNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkNode reverseLink = reverseLink(head.next);

        head.next.next = head;
        head.next = null;

        return reverseLink;
    }

    /**
     * 015-反转单链表 非递归
     *
     * @param head
     * @return
     */
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

    /**
     * 016-合并链表
     * <p>
     * 输入两个单调递增的链表，输出两个链表合成后的链表，
     * 当然我们需要合成后的链表满足单调不减规则。
     *
     * @param list1
     * @param list2
     * @return
     */
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

        LinkNode p = new LinkNode(-1);
        LinkNode cur = p;

        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }

        return p.next;
    }


    /**
     * 025-复杂链表的复制
     *
     * @param pHead
     * @return
     */
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

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }


    /**
     * 036 - 两个链表的第一个公共结点
     * 输入两个链表，找出它们的第一个公共结点。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public LinkNode FindFirstCommonNode(LinkNode pHead1, LinkNode pHead2) {
        LinkNode p = pHead1;
        LinkNode q = pHead2;
        while (p != q) {
            p = p != null ? p.next : pHead2;
            q = q != null ? q.next : pHead1;
        }
        return p;
    }

    /**
     * 055-链表中环的入口结点
     *
     * @param pHead
     * @return
     */
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

    /**
     * 056-删除有序链表的重复节点
     *
     * @param pHead
     * @return
     */
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

    /**
     * 链表的分区
     *
     * @param head
     * @param x
     * @return
     */
    public LinkNode partition(LinkNode head, int x) {
        if (head == null) {
            return head;
        }
        LinkNode l1 = new LinkNode(-1);
        LinkNode l2 = new LinkNode(-1);
        LinkNode p = l1, q = l2;
        while (head != null) {
            if (head.value < x) {
                p.next = head;
                p = p.next;
            } else {
                q.next = head;
                q = q.next;
            }
            head = head.next;
        }
        q.next = null;
        p.next = l2.next;
        return l1.next;
    }

    public LinkNode addTwoNumbers(LinkNode l1, LinkNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.value);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.value);
            l2 = l2.next;
        }

        LinkNode head = null;
        int flag = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || flag > 0) {
            int sum = 0;
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            sum += flag;
            flag = sum / 10;
            LinkNode node = new LinkNode(sum % 10);
            node.next = head;
            head = node;
        }
//        if (flag > 0) {
//            p.next = new LinkNode(flag);
//        }
        return head;
    }


    public static void main(String[] args) {
        LinkNode t = new LinkNode(-1);
        LinkNode head = new LinkNode(7);
        LinkNode head1 = new LinkNode(2);
        LinkNode head2 = new LinkNode(4);
        LinkNode head3 = new LinkNode(3);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;

        LinkNode h = new LinkNode(5);
        LinkNode h1 = new LinkNode(6);
        LinkNode h2 = new LinkNode(4);
        h.next = h1;
        h1.next = h2;
        LinkNode linkNode = t.addTwoNumbers(head, h);
        printLink(linkNode);

//        LinkNode partition = t.partition(head, 3);
//        printLink(partition);


    }

}
