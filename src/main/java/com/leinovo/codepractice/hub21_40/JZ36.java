package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。如下图所示
 * 数据范围：输入二叉树的节点数 0≤n≤1000，二叉树中每个节点的值 0≤val≤1000
 * 要求：空间复杂度O(1)（即在原树上操作），时间复杂度 O(n)
 * <p>
 * 注意:
 * 1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
 * 2.返回链表中的第一个节点的指针
 * 3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
 * 4.你不用输出双向链表，程序会根据你的返回值自动打印输出
 *
 * @description: JZ36
 * @author: leiming5
 * @date: 2021/11/18 19:22
 */
public class JZ36 {

    /**
     * 1. 中序遍历
     * 2. 调整节点指针
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }

        List<TreeNode> list = new ArrayList<>();
        inTravel(pRootOfTree, list);

        resetPoint(list);

        return list.get(0);
    }

    private void resetPoint(List<TreeNode> list) {

        int size = list.size();
        if (size == 0 || size == 1) {
            return;
        }

        for (int i = 0; i < size; i++) {
            list.get(i).left = i == 0 ? null : list.get(i - 1);
            list.get(i).right = i == size - 1 ? null : list.get(i + 1);
        }
    }

    private void inTravel(TreeNode root, List<TreeNode> list) {
        if (root.left != null) {
            inTravel(root.left, list);
        }
        list.add(root);
        if (root.right != null) {
            inTravel(root.right, list);
        }
    }


}
