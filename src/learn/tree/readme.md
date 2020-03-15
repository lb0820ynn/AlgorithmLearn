## 树类题目

- 004-重建二叉树

  ### 题目描述

  输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

  ### 思路

  - 用前序遍历找到根结点

  - 用根结点在中序遍历中切开左右子树，递归重建二叉树

    ```java
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
            if (pre == null || in == null) {
                return null;
            }
            return rebuildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    
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
    ```

    

- 017-树的子结构

  ### 题目描述

  输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

  ### 思路

  - 遍历父结构

  - 判断子结构是否相同

    ```java
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
    ```

    

- 018-二叉树的镜像

  ### 题目描述

  操作给定的二叉树，将其变换为源二叉树的镜像。 输入描述:

  ```
  二叉树的镜像定义：源二叉树 
      	    8
      	   /  \
      	  6   10
      	 / \  / \
      	5  7 9 11
      	镜像二叉树
      	    8
      	   /  \
      	  10   6
      	 / \  / \
      	11 9 7  5
  ```

  ### 思路

  左右交换

  ```java
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
  ```

  

- 022-从上往下打印二叉树

  ### 题目描述

  从上往下打印出二叉树的每个节点，同层节点从左至右打印。

  ### 思路

  二叉树层次遍历，用队列存储每层结点，再依次弹出。

  ```java
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
  ```

  

- 023-二叉搜索树的后序遍历序列

    ### 题目描述

    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

    ### 思路

    二叉搜索树有：

    - 结点值:左<根<右
    - 左右子树都是搜索树

    后序遍历顺序为：左->右->根

    - 最后一个数为根结点，通过根节点值切割左右子树。
    - 判断左右子树是否二叉搜索树

    对于[4,8,6,12,16,14,10]

    ```java
        10
     6     14  
    4 8  12   16
    ```

    ```java
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
    ```

    

- 024-二叉树中和为某一值的路径

    ### 题目描述

    输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的靠前)

    ### 思路

    先序遍历：

    - 每次访问一个节点，那么就将当前权值求和
    - 如果当前权值和与期待的和一致，那么说明我们找到了一个路径，保存或者输出
    - 每次深度遍历到底部，回退一个点 

    ```java
    private static ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private static ArrayList<Integer> list = new ArrayList<>();
    
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
    ```

    

- **026-二叉搜索树与双向链表**

    ### 题目描述

    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

    ### 思路

    二叉搜索树性质有：

    - 没有相同结点
    - 值：左<根<右

    因此我们需要，中序遍历中：

    - pre.right = curr
    - curr.left = pre

    ```java
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
    ```

- 038-二叉树的深度

    ### 题目描述

    输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

    ### 思路

    层序遍历，easy题。

    ```java
    /**
         * 038-二叉树的深度(递归)
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
    ```

- 039-平衡二叉树

    ### 题目描述

    输入一棵二叉树，判断该二叉树是否是平衡二叉树。

    ### 思路

    思路1：非剪枝

    递归查看每棵子树是否满足平衡二叉树，o(nlogn)复杂度。

    思路2：剪枝

    看子树是否是平衡二叉树，如果不是返回-1，如果是返回长度。

    ```java
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
    ```

- 057-二叉树的下一个结点

    ### 题目描述

    给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

    ```java
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
    ```

    

- 058-对称的二叉树

    ### 题目描述

    请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

    ### 思路

    思路1. 正常的层序遍历

    思路2. 两种中序遍历

    - 1. 左->根->右
    - 1. 右->根->左

    ```java
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
    ```

    

- 059-按之字形顺序打印二叉树

    ### 题目描述

    请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

    ### 思路

    层序打印即可。

    ```java
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            int layer = 1;
            //s1存奇数层节点
            Stack<TreeNode> s1 = new Stack<>();
            s1.push(pRoot);
            //s2存偶数层节点
            Stack<TreeNode> s2 = new Stack<>();
    
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    
            while (!s1.empty() || !s2.empty()) {
                if (layer%2 != 0) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    while (!s1.empty()) {
                        TreeNode node = s1.pop();
                        if(node != null) {
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
                        if(node != null) {
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
    ```

    

- 060-把二叉树打印成多行

    ### 题目描述

    从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

    ### 思路

    层序打印即可。

    ```java
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
    ```

    

- 061-序列化二叉树

    ### 题目描述

    请实现两个函数，分别用来序列化和反序列化二叉树

     

    ```java
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
    ```

    

- 062-二叉搜索树的第k个结点

    ### 题目描述

    给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8） 中，按结点数值大小顺序第三小结点的值为4。

    ### 思路

    中序遍历第k个数

    ```java
     int index = 0; //计数器
     TreeNode KthNode(TreeNode root, int k) {
            if (root != null) { //中序遍历寻找第k个
                TreeNode node = KthNode(root.left, k);
                if (node != null)
                    return node;
                index++;
                if (index == k)
                    return root;
                node = KthNode(root.right, k);
                if (node != null)
                    return node;
            }
            return null;
        }
    ```

    