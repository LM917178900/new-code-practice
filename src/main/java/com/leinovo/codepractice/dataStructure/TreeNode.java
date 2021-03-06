package com.leinovo.codepractice.dataStructure;

/**
 * http://data.biancheng.net/view/189.html
 * <p>
 * 满二叉树：从高到低，除了叶结点外，所有结点的左右结点都存在。
 * 完全二叉树：比满二叉树少几个叶结点，从左向右放子结点。
 * 平衡二叉树：空树或者它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树也都是平衡树。
 * 二叉搜索树：空树或者二叉树的所有结点比它的左子结点大，比它的右子结点小。
 * 原文链接：https://blog.csdn.net/u013132035/article/details/80607000
 *
 * @description: TreeNode
 * @author: leiming5
 * @date: 2021/10/9 18:44
 */
public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    /**
     *  二叉树（binary tree）
     *  是指树中节点的度不大于2的有序树，它是一种最简单且最重要的树。
     *  二叉树的递归定义为：二叉树是一棵空树，或者是一棵由一个根节点和两棵互不相交的，分别称作根的左子树和右子树组成的非空树；
     *  左子树和右子树又同样都是二叉树 。
     */
    /**
     *  满二叉树：如果一棵二叉树只有度为0的结点和度为2的结点，并且度为0的结点在同一层上，则这棵二叉树为满二叉树  。
     *          结点要么是叶子结点，要么它有两个子结点，这样的树就是满二叉树。
     *          从图形形态上看，满二叉树外观上是一个三角形。
     *
     * 完全二叉树：深度为k，有n个结点的二叉树当且仅当其每一个结点都与深度为k的满二叉树中编号从1到n的结点一一对应时，称为完全二叉树  。
     */
    /**
     * 二叉排序树（Binary Sort Tree），又称二叉查找树（Binary Search Tree），亦称二叉搜索树。
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
     * <p>
     * 二叉排序树的操作主要有：
     * 1.查找：递归查找是否存在key。
     * 2.插入：原树中不存在key，插入key返回true，否则返回false。
     * 3.构造：循环的插入操作。
     * 4.删除：（1）叶子节点：直接删除，不影响原树。
     * （2）仅仅有左或右子树的节点：节点删除后，将它的左子树或右子树整个移动到删除节点的位置就可以，子承父业。
     * （3）既有左又有右子树的节点：找到须要删除的节点p的直接前驱或者直接后继s，用s来替换节点p，然后再删除节点s。
     */
    public TreeNode(int val) {
        this.val = val;
    }

    public Integer getDeepOfTree(Integer deep) {
        TreeNode root = this;
        if (root == null) {
            return deep;
        }

        deep++;
        int left = root.left.getDeepOfTree(deep);
        int right = root.right.getDeepOfTree(deep);

        return left > right ? left : right;
    }

    public TreeNode deepCopy() {

        TreeNode pRoot = new TreeNode(this.val);
        pRoot.left = this.left.deepCopy();
        pRoot.right = this.right.deepCopy();

        return pRoot;
    }


}
