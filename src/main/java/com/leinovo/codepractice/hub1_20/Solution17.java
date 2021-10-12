package com.leinovo.codepractice.hub1_20;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 * @description: Solution17
 * @author: leiming5
 * @date: 2021/10/9 18:45
 */
public class Solution17 {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        // 1.如果都为 null 判断不是子结构
        if (root1 == null || root2 == null) {
            return false;
        }

        // 2.匹配当前节点
        boolean current = root1.val == root2.val && subTree(root1, root2);
        // 3.匹配左节点
        boolean left = HasSubtree(root1.left, root2);
        // 4.匹配右节点
        boolean right = HasSubtree(root1.right, root2);

        return current || left || right;
    }

    public boolean subTree(TreeNode root1, TreeNode root2) {
        // 1. 被匹配者为空，匹配成功
        if (root2 == null) {
            return true;
        }
        // 2.匹配者为空，匹配失败
        if (root1 == null) {
            return false;
        }
        // 3.匹配值
        boolean value = root1.val == root2.val;
        // 4.匹配左树
        boolean left = subTree(root1.left, root2.left);
        // 5.匹配右树
        boolean right = subTree(root1.right, root2.right);

        return value && left && right;

    }
}
