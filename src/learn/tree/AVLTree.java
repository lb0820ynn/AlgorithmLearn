package learn.tree;

/**
 * 二叉平衡树（AVL树）
 * Created by liubin on 2020/3/27.
 */
public class AVLTree<T extends Comparable<T>> {
    AVLTreeNode<T> mRoot;

    public AVLTree() {
        mRoot = null;
    }

    class AVLTreeNode<T extends Comparable<T>> {
        T key;
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;
        int height;

        public AVLTreeNode(T key, AVLTreeNode left, AVLTreeNode right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    private void preOrder(AVLTreeNode<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    private void inOrder(AVLTreeNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    private void postOrder(AVLTreeNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    private int height(AVLTreeNode<T> node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }

    public int height() {
        return height(mRoot);
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     *
     * @param key  插入的结点的键值
     * @param node
     * @return
     */
    private AVLTreeNode<T> insert(T key, AVLTreeNode<T> node) {
        if (node == null) {
            node = new AVLTreeNode<>(key, null, null);
        } else {
            int cp = key.compareTo(node.key);
            if (cp < 0) {
                node.left = insert(key, node.left);
                if (height(node.left) - height(node.right) == 2) {
                    if (key.compareTo(node.left.key) < 0) {
                        node = leftLeftRotation(node);
                    } else {
                        node = leftRightRotation(node);
                    }
                }
            } else if (cp > 0) {
                node.right = insert(key, node.right);
                if (height(node.right) - height(node.left) == 2) {
                    if (key.compareTo(node.right.key) > 0) {
                        node = rightRightRotation(node);
                    } else {
                        node = rightLeftRotation(node);
                    }
                }
            } else {
                throw new RuntimeException("不允许添加相同的结点");
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void insert(T key) {
        mRoot = insert(key, mRoot);
    }


    public AVLTreeNode<T> remove(T key) {
        return remove(key, mRoot);
    }

    /**
     * 删除key 返回根结点
     *
     * @param key
     * @param node
     * @return
     */
    private AVLTreeNode<T> remove(T key, AVLTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        int cp = key.compareTo(node.key);
        if (cp < 0) {   // 待删除的节点在"tree的左子树"中
            node.left = remove(key, node.left);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(node.right) - height(node.left) == 2) {
                AVLTreeNode<T> right = node.right;
                if (height(right.left) > height(right.right)) {
                    node = rightLeftRotation(node);
                } else {
                    node = rightRightRotation(node);
                }
            }
        } else if (cp > 0) {    // 待删除的节点在"tree的右子树"中
            node.right = remove(key, node.right);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(node.left) - height(node.right) == 2) {
                AVLTreeNode<T> left = node.left;
                if (height(left.right) > height(left.left)) {
                    node = leftRightRotation(node);
                } else {
                    node = leftLeftRotation(node);
                }
            }
        } else if (node.left != null && node.right != null) {// node是对应要删除的节点。
            // node的左右孩子都非空
            if (height(node.left) > height(node.right)) {
                // 如果node的左子树比右子树高；
                // 则(01)找出node的左子树中的最大节点
                //   (02)将该最大节点的值赋值给node。
                //   (03)删除该最大节点。
                // 这类似于用"node的左子树中最大节点"做"node"的替身；
                // 采用这种方式的好处是：删除"node的左子树中最大节点"之后，AVL树仍然是平衡的。
                AVLTreeNode<T> max = maxNode(node.left);
                node.key = max.key;
                node.left = remove(max.key, node.left);
            } else {
                AVLTreeNode<T> min = minNode(node.right);
                node.key = min.key;
                node.right = remove(min.key, node.right);
            }
        } else {
            node = node.left != null ? node.left : node.right;
        }

        return node;
    }

    private AVLTreeNode<T> minNode(AVLTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        while (node != null) {
            node = node.left;
        }
        return node;
    }

    public T minimum() {
        AVLTreeNode<T> node = minNode(mRoot);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private AVLTreeNode<T> maxNode(AVLTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        while (node != null) {
            node = node.right;
        }
        return node;
    }

    public T maximum() {
        AVLTreeNode<T> node = maxNode(mRoot);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    /**
     * LR：左右对应的情况(左双旋转)。
     *
     * @param k3
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3) {
        k3.left = rightRightRotation(k3.left);
        return leftLeftRotation(k3);
    }

    /**
     * RL：右左对应的情况(右双旋转)。
     *
     * @param k3
     * @return 旋转后的根节点
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k3) {
        k3.right = leftLeftRotation(k3.right);
        return rightRightRotation(k3);
    }

    /**
     * LL：左左对应的情况(左单旋转)。
     *
     * @param k2
     * @return
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }

    /**
     * RR：右右对应的情况(右单旋转)。
     *
     * @param k2
     * @return
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;

        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;

        return k1;
    }

    private AVLTreeNode<T> search(T key, AVLTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        int cp = key.compareTo(node.key);
        if (cp < 0) {
            node = search(key, node.left);
        } else if (cp > 0) {
            node = search(key, node.right);
        }
        return node;
    }

    private AVLTreeNode<T> iterativeSearch(T key, AVLTreeNode<T> node) {
        while (node != null) {
            int cp = key.compareTo(node.key);
            if (cp < 0) {
                node = node.left;
            } else if (cp > 0) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }


    /**
     * 打印"二叉查找树"
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     * -1，表示该节点是它的父结点的左孩子;
     * 1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLTreeNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key, key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }

}
