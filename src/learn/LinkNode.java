package learn;

import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        LinkNode head = new LinkNode(1);
        LinkNode head1 = new LinkNode(3);
        LinkNode head2 = new LinkNode(5);
        LinkNode head3 = new LinkNode(7);
        LinkNode head4 = new LinkNode(9);

        LinkNode headOther = new LinkNode(2);
        LinkNode headOther1 = new LinkNode(4);
        LinkNode headOther2 = new LinkNode(6);
        LinkNode headOther3 = new LinkNode(8);
        LinkNode headOther4 = new LinkNode(10);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;

        headOther.next = headOther1;
        headOther1.next = headOther2;
        headOther2.next = headOther3;
        headOther3.next = headOther4;

        LinkNode reverse = reverseLink(head);
//
        printLink(reverse);
//
//        LinkNode node = getReverseNodeItem(reverse, 2);
//        System.out.println();
//        System.out.println(node.value);

//        ArrayList<Integer> integers = printListFromTailToHead(head);
//
//        Utils.printList(integers);

//        LinkNode node = FindKthToTail(head, 6);
//        System.out.print(node.value);

//        reversePrintLink(reverse);

        printLink(Merge(head, headOther));


    }

    public static void printLink(LinkNode head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    /**
     * 反转单链表 递归
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
     * 反转单链表 非递归
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
     * 逆序打印链表 递归
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
     * 获取到倒数第k个节点
     *
     * @param head
     * @param k
     */
    public static LinkNode getReverseNodeItem(LinkNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkNode fir = head;
        LinkNode sec = head;

        while (fir != null && k > 0) {
            fir = fir.next;
            k--;
        }

        if (fir == null) {
            return null;
        }

        while (fir != null) {
            fir = fir.next;
            sec = sec.next;
        }

        return sec;
    }

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
     * 获取到倒数第k个节点
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
     * 合并链表
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

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode mHead = head;
        while (pHead.next != null) {
            RandomListNode temp = pHead.next;
            RandomListNode random = pHead.random;

            RandomListNode node = new RandomListNode(temp.label);
            if (random != null) {
                RandomListNode nodeRandom = new RandomListNode(random.label);
                mHead.random = nodeRandom;
            } else {
                mHead.random = null;
            }
            mHead.next = node;
            pHead = temp;
            mHead = mHead.next;
        }

        return head;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }


}
