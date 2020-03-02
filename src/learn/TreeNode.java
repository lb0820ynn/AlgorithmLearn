package learn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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


        TreeNode node = new TreeNode(4);
        TreeNode node1 = new TreeNode(7);
//        TreeNode node3 = new TreeNode(6);
        TreeNode node2 = new TreeNode(5, node, node1);
        TreeNode node4 = new TreeNode(12, null, null);
        TreeNode root = new TreeNode(10, node2, node4);


//        boolean hasSubtree = HasSubtree(root, rootOther);
//        System.out.print("HasSubTree == " + hasSubtree);

//        Mirror(root);
//
//        firTraver(root);

        lastTraver(root);

        System.out.println();
//        printFromTopToBottom(root);

        ArrayList<ArrayList<Integer>> list = FindPath(root, 19);
        Utils.printList(list);

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
    }

    public static void firTraver(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");

        firTraver(root.left);

        firTraver(root.right);
    }

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

    public static int getNum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return getNum(node.left) + getNum(node.right) + 1;

    }

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

    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

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

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        return rebuildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6}

    /**
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

//    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
//
//    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
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

    private static ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private static ArrayList<Integer> list = new ArrayList<>();

    /**
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


//    public static TreeNode Convert(TreeNode pRootOfTree) {
//        if(pRootOfTree == null){
//            return null;
//        }
//    }


}
