package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: JZ28
 * @author: leiming5
 * @date: 2021/11/16 17:14
 */
public class JZ28 {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode le3 = new TreeNode(3);
        root.left = left;
        left.right = le3;

        boolean symmetrical = isSymmetrical(root);
        System.out.println(symmetrical);

    }

    /**
     * 1. 镜像当前的树，
     * 2. 对比交换前后的树；
     * 3. 判断其中的值，是否相等，如果相等，就是对称树
     *
     * @param pRoot
     * @return
     */
    public static boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }

        return isSame(pRoot,pRoot);
    }

    public static boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        boolean val = root1 == null || root2 == null;
        if (val) {
            return false;
        }

        boolean middle = root1.val == root2.val;
        if (!middle) {
            return false;
        }
        boolean leftValidate = isSame(root1.left, root2.right);
        if (!leftValidate) {
            return false;
        }
        boolean rightValidate = isSame(root2.left, root1.right);
        if (!rightValidate) {
            return false;
        }
        return true;
    }

    public static boolean isSymmetricalBack(TreeNode pRoot) {

        if (pRoot == null) {
            return true;
        }

        TreeNode root = deepCopy(pRoot);
        revertTreeNode(root);

        boolean result = validateSame(pRoot, root);

        return result;
    }

    private static boolean validateSame(TreeNode pRoot, TreeNode root) {

        if (pRoot == null && root == null) {
            return true;
        }
        boolean val = (pRoot == null && root != null) || (pRoot != null && root == null);
        if (val) {
            return false;
        }

        boolean current = pRoot.val == root.val;
        if (!current) {
            return false;
        }

        boolean left = validateSame(pRoot.left, root.left);
        if (!left) {
            return false;
        }
        boolean right = validateSame(pRoot.right, root.right);
        if (!right) {
            return false;
        }

        return true;
    }

    /**
     * 反转 treeNode
     *
     * @param root
     */
    public static void revertTreeNode(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode left = poll.left;
            TreeNode right = poll.right;

            TreeNode temp = left;
            poll.left = right;
            poll.right = temp;

            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }

    /**
     * 复制一个 TreeNode
     *
     * @param root
     * @return
     */
    public static TreeNode deepCopy(TreeNode root) {

        TreeNode pRoot = new TreeNode(root.val);
        if (root.left != null) {
            pRoot.left = deepCopy(root.left);
        }
        if (root.right != null) {
            pRoot.right = deepCopy(root.right);
        }

        return pRoot;
    }

}
