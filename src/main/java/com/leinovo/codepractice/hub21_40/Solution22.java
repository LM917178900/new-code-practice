package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Solution22
 * @author: leiming5
 * @date: 2021/10/12 19:51
 */
public class Solution22 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode level2 = new TreeNode(4);
        TreeNode level3 = new TreeNode(3);
        TreeNode level4 = new TreeNode(2);
        TreeNode level5 = new TreeNode(1);
        root.left = level2;
        level2.left = level3;
        level3.left = level4;
        level4.left = level5;

        ArrayList<Integer> result = PrintFromTopToBottom(root);
        System.out.println(result.toString());

    }

    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }

        //queue用来保存当前遍历到了哪个节点，把是状结构转化为了线性结构；
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode current = root;
        queue.offer(current);
        //只要队列中还有节点就说明还没遍历完，继续。
        //每次从队列出队，然后将这个节点左右子入队列（FIFO，故能完成广度/层级遍历），再将这个节点记录在list中即可。
        while (!queue.isEmpty()) {

            current = queue.poll();
            list.add(current.val);

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return list;
    }

    public static ArrayList<Integer> PrintFromTopToBottomback(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // 1. 当前节点,val
        // 2. 获取每层所有节点
        ArrayList<ArrayList<TreeNode>> lists = new ArrayList<>();
        Integer deep = 0;
        deep = root.getDeepOfTree(deep);


        // 分别获取每层的节点；加入lists中；
        for (int i = 0; i <= deep; i++) {
            Integer tempDeep = 0;
            ArrayList<TreeNode> targetList = new ArrayList<>();
            getTarget(root, tempDeep, i, targetList);

            lists.add(targetList);
        }

        // 获取节点的val
        for (ArrayList<TreeNode> list : lists) {
            for (TreeNode treeNode : list) {
                result.add(treeNode.val);
            }
        }

        return result;
    }

    /**
     * 获取指定层的数据
     *
     * @return
     */
    private static void getTarget(TreeNode root, Integer tempDeep, int targetDeep, ArrayList<TreeNode> targetList) {

        if (root == null) {
            return;
        }

        if (tempDeep < targetDeep) {
            tempDeep++;
            getTarget(root.left, tempDeep, targetDeep, targetList);
            getTarget(root.right, tempDeep, targetDeep, targetList);
        } else if (targetDeep == tempDeep) {
            targetList.add(root);
        }

    }


}
