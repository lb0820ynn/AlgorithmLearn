package learn.tree;

/**
 * 二叉搜索树
 * Created by liubin on 2020/4/7.
 */
public class BinarySearchTree {

    private class Node {
        Node left;
        Node right;
        int value;

        public Node(int val) {
            this.value = val;
        }
    }

    private Node root;

    public Node insert(int val, Node node) {
        if (node == null) {
            node = new Node(val);
        } else {
            if (val < node.value) {
                node.left = insert(val, node.left);
            } else if (val > node.value) {
                node.right = insert(val, node.right);
            } else {
                throw new RuntimeException("不允许添加相同的结点");
            }
        }
        return node;
    }

    private Node delete(int val) {
        return delete(val, root);
    }

    private Node delete(int val, Node root) {
        if (root == null) {
            return root;
        }
        if (val < root.value) {
            root.left = delete(val, root.left);
        } else if (val > root.value) {
            root.right = delete(val, root.right);
        } else if (root.left != null && root.right != null) {
            root.value = findMin(root.right).value;
            delete(root.value, root.right);
        } else {
            root = root.left != null ? root.left : root.right;
        }
        return root;
    }

    private Node findMin(Node node) {
        if (node == null) {
            return node;
        }
        while (node != null) {
            node = node.left;
        }
        return node;
    }


}
