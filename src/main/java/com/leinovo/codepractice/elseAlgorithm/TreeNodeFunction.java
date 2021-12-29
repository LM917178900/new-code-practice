package com.leinovo.codepractice.elseAlgorithm;

import com.leinovo.codepractice.dataStructure.TreeNode;

/**
 * 二叉排序树（Binary Sort Tree），又称二叉查找树（Binary Search Tree），亦称二叉搜索树。
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
 * <p>
 * 二叉排序树的操作主要有：
 * 1.查找：递归查找是否存在key。
 * 2.插入：原树中不存在key，插入key返回true，否则返回false。
 * 3.构造：循环的插入操作。（后续数组生成二叉树，Solution23.generate()）
 * 4.删除：（1）叶子节点：直接删除，不影响原树。
 * （2）仅仅有左或右子树的节点：节点删除后，将它的左子树或右子树整个移动到删除节点的位置就可以，子承父业。
 * （3）既有左又有右子树的节点：找到须要删除的节点p的直接前驱或者直接后继s，用s来替换节点p，然后再删除节点s。
 *
 * @description: TreeNodeFunction
 * @author: leiming5
 * @date: 2021/10/15 9:17
 */
public class TreeNodeFunction {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(8);
        TreeNode right = new TreeNode(18);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(14);
        TreeNode node4 = new TreeNode(25);

        root.left = left;
        root.right = right;
        left.left = node1;
        left.right = node2;
        right.left = node3;
        right.right = node4;

        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(15);
        node3.left = node5;
        node3.right = node6;

        TreeNode node7 = new TreeNode(23);
        TreeNode node8 = new TreeNode(20);
        TreeNode node9 = new TreeNode(24);
        node7.left = node8;
        node7.right = node9;
        node4.left = node7;

        TreeNode node10 = new TreeNode(27);
        node4.right = node10;
        TreeNode node11 = new TreeNode(1);
        node1.left = node11;
//        boolean result = insertSave(root, 5);
//        List<Integer> result = new ArrayList<>();
//        boolean result = search(root, 13);
        deleteByVal(root, 6);
        System.out.println("delete");
    }

    /**
     * 叶子节点：直接删除
     * 只有一个子节点：删除当前节点，用子节点代替
     * 有两个子节点：删除的一般流程
     * 1. 找到对应的节点，标记为target1
     * 2. 找到该节点右子树的最小节点，标记为target2
     * 3. 将target1 的值替换为target2，的值；
     * 4. 删除target2节点；
     *
     * @param root 原始节点；
     * @param val  需要删除的值
     * @author leiming5
     */
    public static void deleteByVal(TreeNode root, int val) {
        TreeNode target1 = searchNodeByVal(root, val);
        if (target1 == null) {
            return;
        }

        TreeNode node1 = target1.left;
        TreeNode node2 = target1.right;
        if (node1 == null && node2 == null) {
//            target1 = null;
            deleteLevel(root,val);
        } else if (node1 != null && node2 == null) {
            target1.val = node1.val;
            target1.left = node1.left;
            target1.right = node1.right;
        } else if (node1 == null && node2 != null) {
            target1.val = node2.val;
            target1.left = node2.left;
            target1.right = node2.right;
        } else {
            TreeNode target2 = getMinFromRight(node2.left);
            int minVal = target2.val;
//            target2 = null;
            deleteLevel(node2.left,minVal);
            target1.val = minVal;
        }
    }

    public static boolean deleteLevel(TreeNode root, int val) {
        if (root == null) {
            return false;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null && left.val == val) {
            root.left = null;
            return true;
        }
        if (right != null && right.val == val) {
            root.right = null;
            return true;
        }

        boolean leftDelete = false;
        if (left != null) {
            leftDelete =deleteLevel(left,val);
        }
        if(leftDelete){
            return true;
        }

        boolean rightDelete = false;
        if (right != null) {
            rightDelete =deleteLevel(right,val);
        }
        return rightDelete;
    }

    private static TreeNode getMinFromRight(TreeNode node2) {

        TreeNode left = node2.left;
        if (left == null) {
            return node2;
        }

        return getMinFromRight(left);

    }


    public static boolean insertSave(TreeNode root, int val) {

        if (root.left == null && root.right == null) {
            TreeNode node = new TreeNode(val);
            if (root.val < val) {
                root.right = node;
            } else {
                root.left = node;
            }
            return true;
        }

        boolean exist = search(root, val);
        if (exist) {
            return false;
        }

        if (root.val < val) {
            insertSave(root.right, val);
        } else {
            insertSave(root.left, val);
        }

        return true;
    }

    public static boolean search(TreeNode root, int val) {

        if (root == null) {
            return false;
        }

        // val
        if (root.val == val) {
            return true;
        }

        TreeNode temp = root;
        while (temp.val != val) {

            if (temp.val == val) {
                return true;
            }

            if (temp.left == null || temp.right == null) {
                return false;
            }

            if (temp.val > val) {
                if (temp.left != null) {
                    temp = temp.left;
                }
            } else {
                if (temp.right != null) {
                    temp = temp.right;
                }
            }
        }
        return false;
    }

    public static TreeNode searchNodeByVal(TreeNode root, int val) {

        if (root == null) {
            return null;
        }

        // val
        if (root.val == val) {
            return root;
        }

        // left
        TreeNode left = searchNodeByVal(root.left, val);
        if (left != null) {
            return left;
        }

        // right
        TreeNode right = searchNodeByVal(root.right, val);
        if (right != null) {
            return right;
        }
        return null;
    }


    // 通过数组/遍历方式生成 二叉树
//    public TreeNode generateTreeNode(int[] arr,String type){
//        //
//
//    }
}
