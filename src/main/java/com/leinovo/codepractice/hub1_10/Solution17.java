package com.leinovo.codepractice.hub1_10;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 * @description: Solution17
 * @author: leiming5
 * @date: 2021/10/9 18:45
 */
public class Solution17 {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        // 如果都为 null 判断不是子结构
        if (root1 == null || root2 == null) {
            return false;
        }
        // root2 是root1 子结构的三种情况：左/中/右
        return isSame(root1, root2) || isSame(root1.left, root2) || isSame(root1.right, root2);
    }

    //    首先：判断结构相同必须需要的函数
    public boolean isSame(TreeNode root1, TreeNode root2) {
        // 如果当前值为null，判断是子结构
        if (root2 == null) {
            return true;
        }
        // 如果跟结构为 null，判断不是子结构
        if (root1 == null) {
            return false;
        }
        // 判断是子结构的情况，必须同时满足：字相等，左边是子结构，右边也是子结构？
        return root1.val == root2.val
                && isSame(root1.left, root2.left)
                && isSame(root1.right, root2.right);
    }

}
