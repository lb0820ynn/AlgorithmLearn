package learn.tree;

import learn.Utils;

import java.util.*;

/**
 * Created by liubin on 2017/6/1.
 */
public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this(value, null, null);
    }

    public TreeNode(int value, TreeNode left) {
        this(value, left, null);
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + (left == null ? "null" : left.value) +
                ", right=" + (right == null ? "null" : right.value) +
                '}';
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public static void firTraver(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");

        firTraver(root.left);

        firTraver(root.right);
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public static void middleTraver(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            middleTraver(root.left);
        }

        System.out.print(root.value + " ");

        if (root.right != null) {
            middleTraver(root.right);
        }

    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public static void lastTraver(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            lastTraver(root.left);
        }
        if (root.right != null) {
            lastTraver(root.right);
        }
        System.out.print(root.value + " ");
    }

    /**
     * 获取节点数量
     *
     * @param node
     * @return
     */
    public static int getNum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return getNum(node.left) + getNum(node.right) + 1;

    }

    /**
     * 非递归获取结点数量
     *
     * @param node
     * @return
     */
    public static int getNum2(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int count = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode remove = queue.remove();

            if (remove.left != null) {
                queue.add(remove.left);
                count++;
            }
            if (remove.right != null) {
                queue.add(remove.right);
                count++;
            }
        }

        return count;
    }

    /**
     * 038-二叉树的深度(递归)
     *
     * @param root
     * @return
     */
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    /**
     * 038-二叉树的深度(非递归)
     *
     * @param root
     * @return
     */
    public static int getDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        int current = 1;
        int next = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.remove();
            current--;

            if (curNode.left != null) {
                queue.add(curNode.left);
                next++;
            }

            if (curNode.right != null) {
                queue.add(curNode.right);
                next++;
            }

            if (current == 0) { //遍历当前层的节点，遍历完成后深度加一
                depth++;
                current = next;
                next = 0;
            }
        }
        return depth;
    }

    /**
     * 004-重建二叉树
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        return rebuildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6}

    /**
     * 004-重建二叉树
     * <p>
     * 前序遍历第一个结点是父结点，中序遍历如果遍历到父结点，那么父结点前面的结点是左子树的结点，
     * 后边的结点的右子树的结点，这样我们可以找到左、右子树的前序遍历和中序遍历，
     * 我们可以用同样的方法去构建左右子树，可以用递归完成。
     *
     * @param pre
     * @param preStart
     * @param preEnd
     * @param in
     * @param inStart
     * @param inEnd
     * @return
     */
    private static TreeNode rebuildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);

        for (int i = inStart; i <= inEnd; i++) {
            if (pre[preStart] == in[i]) {

                root.left = rebuildTree(pre, preStart + 1, preStart + (i - inStart),
                        in, inStart, i - 1);

                root.right = rebuildTree(pre, preStart + (i - inStart) + 1, preEnd,
                        in, i + 1, inEnd);
            }
        }

        return root;
    }

    /**
     * 017-树的子结构
     * <p>
     * 输入两棵二叉树A，B，判断B是不是A的子结构。
     * （ps：我们约定空树不是任意一个树的子结构）
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }

        return isSubTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private static boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.value == root2.value) {
            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    /**
     * 018-二叉树的镜像
     *
     * @param root
     */
    public static void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * 018-二叉树的镜像 非递归
     *
     * @param node
     */
    private static void mirro(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            TreeNode left = tmp.left;
            TreeNode right = tmp.right;
            tmp.right = left;
            tmp.left = right;
            if (left != null) {
                stack.push(left);
            }
            if (right != null) {
                stack.push(right);
            }
        }
    }

    /**
     * 022-从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * @param root
     */
    public static void printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.remove();
            result.add(curNode.value);
            if (curNode.left != null) {
                queue.add(curNode.left);
            }

            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }
    }

    /**
     * 023-二叉搜索树的后序遍历序列
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 0) return false;
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean VerifySquenceOfBST(int[] sequence, int start, int end) {
        if (start >= end)
            return true;

        int root = sequence[end];
        int i = start;
        while (sequence[i] < root) {
            i++;
        }

        int j = i;
        while (j < end) {
            if (sequence[j] < root) {
                return false;
            }
            j++;
        }

        boolean left = VerifySquenceOfBST(sequence, start, i - 1);
        boolean right = VerifySquenceOfBST(sequence, i, end - 1);
        return left && right;
    }

    private static ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private static ArrayList<Integer> list = new ArrayList<>();

    /**
     * 024-二叉树中和为某一值的路径
     * <p>
     * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
     *
     * @param root
     * @param target
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return listAll;
        }
        list.add(root.value);
        target -= root.value;
        if (target == 0 && root.left == null && root.right == null) {
            listAll.add(new ArrayList<Integer>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }


    private static TreeNode pLast = null;


    /**
     * 026-二叉搜索树与双向链表
     *
     * @param root
     * @return
     */
    public static TreeNode Convert(TreeNode root) {
        if (root == null)
            return null;

        // 如果左子树为空，那么根节点root为双向链表的头节点
        TreeNode head = Convert(root.left);
        if (head == null)
            head = root;

        // 连接当前节点root和当前链表的尾节点pLast
        System.out.println("root1 == " + root + " plast1 == " + pLast);
        root.left = pLast;
        if (pLast != null)
            pLast.right = root;
        pLast = root;
//        System.out.print("root == " + root.value + " plast == " + pLast.value);

        Convert(root.right);
        return head;
    }

    /**
     * 039-平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        if (left == -1) return -1;
        int right = depth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    /**
     * 057-二叉树的下一个结点
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            TreeLinkNode temp = pNode.next;
            if (pNode == temp.left) {
                return temp;
            }
            pNode = pNode.next;
        }
        return null;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 058-对称的二叉树
     *
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.value == right.value &&
                isSymmetrical(left.left, right.right) &&
                isSymmetrical(left.right, right.left);
    }

    /**
     * 059-按之字形顺序打印二叉树
     *
     * @param pRoot
     * @return
     */
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        int layer = 1;
        //s1存奇数层节点
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(pRoot);
        //s2存偶数层节点
        Stack<TreeNode> s2 = new Stack<>();

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        while (!s1.empty() || !s2.empty()) {
            if (layer % 2 != 0) {
                ArrayList<Integer> temp = new ArrayList<>();
                while (!s1.empty()) {
                    TreeNode node = s1.pop();
                    if (node != null) {
                        temp.add(node.value);
                        System.out.print(node.value + " ");
                        s2.push(node.left);
                        s2.push(node.right);
                    }
                }
                if (!temp.isEmpty()) {
                    list.add(temp);
                    layer++;
                    System.out.println();
                }
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                while (!s2.empty()) {
                    TreeNode node = s2.pop();
                    if (node != null) {
                        temp.add(node.value);
                        System.out.print(node.value + " ");
                        s1.push(node.right);
                        s1.push(node.left);
                    }
                }
                if (!temp.isEmpty()) {
                    list.add(temp);
                    layer++;
                    System.out.println();
                }
            }
        }
        return list;
    }

    /**
     * 060-把二叉树打印成多行
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print60(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> layer = new LinkedList<>();
        ArrayList<Integer> layerList = new ArrayList<>();
        layer.add(pRoot);
        int start = 0, end = 1;
        while (!layer.isEmpty()) {
            TreeNode cur = layer.remove();
            layerList.add(cur.value);
            start++;
            if (cur.left != null) {
                layer.add(cur.left);
            }
            if (cur.right != null) {
                layer.add(cur.right);
            }
            if (start == end) {
                end = layer.size();
                start = 0;
                result.add(layerList);
                layerList = new ArrayList<>();
            }
        }
        return result;
    }

    int index = -1;   //计数变量

    /**
     * 061-序列化二叉树(序列化)
     */
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.value + ",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }

    /**
     * 061-序列化二叉树(反序列化)
     */
    TreeNode Deserialize(String str) {
        index++;
        String[] strr = str.split(",");
        TreeNode node = null;
        if (!strr[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }

    int indexK = 0; //计数器

    /**
     * 062-二叉搜索树的第k个结点
     *
     * @param root
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode root, int k) {
        if (root != null) { //中序遍历寻找第k个
            TreeNode node = KthNode(root.left, k);
            if (node != null)
                return node;
            indexK++;
            if (indexK == k)
                return root;
            node = KthNode(root.right, k);
            if (node != null)
                return node;
        }
        return null;
    }


    public static void main(String[] args) {
//        TreeNode node = new TreeNode(4);
//        TreeNode node1 = new TreeNode(5);
//        TreeNode node3 = new TreeNode(6);
//        TreeNode node2 = new TreeNode(2, node, node1);
//        TreeNode node4 = new TreeNode(3, null, node3);
//        TreeNode root = new TreeNode(1, node2, node4);
//
//
//        TreeNode nodeOther1 = new TreeNode(4);
//        TreeNode nodeOther2 = new TreeNode(5);
//        TreeNode rootOther = new TreeNode(2, nodeOther1, nodeOther2);


//        TreeNode node = new TreeNode(4);
//        TreeNode node1 = new TreeNode(7);
////        TreeNode node3 = new TreeNode(6);
//        TreeNode node2 = new TreeNode(5, node, node1);
//        TreeNode node4 = new TreeNode(12, null, null);
//        TreeNode root = new TreeNode(10, node2, node4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7, node6);
        TreeNode node4 = new TreeNode(4, node5, node7);
        TreeNode node3 = new TreeNode(3, null, node4);
        TreeNode node1 = new TreeNode(1, node2, node3);
//        TreeNode node3 = new TreeNode(6);


//        boolean hasSubtree = HasSubtree(root, rootOther);
//        System.out.print("HasSubTree == " + hasSubtree);

//        Mirror(root);
//
//        firTraver(root);

        middleTraver(node1);

        System.out.println();
//        printFromTopToBottom(root);

//        ArrayList<ArrayList<Integer>> list = FindPath(root, 19);
//        Utils.printList(list);

        /**
         1
         / \
         2   3
         / \   \
         4   5   6
         */

//        firTraver(root);
//
//        System.out.println();
//        middleTraver(root);
//        System.out.println();
//        lastTraver(root);
//
//        int num = getNum2(root);
//
//        System.out.print("tree node == " + num);
//
//        System.out.println();
//
//        int depth = getDepth2(root);
//        System.out.print("tree depth == " + depth);

//        TreeNode treeNode1 = reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
//        firTraver(treeNode1);
//        middleTraver(treeNode1

        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n2, n4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7, n6, n8);
        TreeNode n5 = new TreeNode(5, n3, n7);
        Convert(n5);

//        TreeNode node1 = new TreeNode(5);
//        TreeNode node3 = new TreeNode(6);
//        TreeNode node2 = new TreeNode(2, node, node1);
//        TreeNode node4 = new TreeNode(3, null, node3);
//        TreeNode root = new TreeNode(1, node2, node4);

    }
}
