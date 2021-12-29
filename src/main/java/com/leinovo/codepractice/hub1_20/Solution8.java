package com.leinovo.codepractice.hub1_20;

import com.leinovo.codepractice.dataStructure.TreeLinkNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针。
 * 下图为一棵有9个节点的二叉树。树中从父节点指向子节点的指针用实线表示，从子节点指向父节点的用虚线表示
 *
 * @description: Solution8
 * @author: leiming5
 * @date: 2021/10/17 19:49
 */
public class Solution8 {
    /**
     * 1. 找到树的根节点
     * 2. 获取根节点所有的中序列表；
     * 3. 根据中序列表，找到下一个节点对象；
     *
     */
    public static void main(String[] args) {
        TreeLinkNode pNode = new TreeLinkNode(8);
        TreeLinkNode left = new TreeLinkNode(6);
        TreeLinkNode right = new TreeLinkNode(10);
        TreeLinkNode node1 = new TreeLinkNode(5);
        TreeLinkNode node2 = new TreeLinkNode(7);
        TreeLinkNode node3 = new TreeLinkNode(9);
        TreeLinkNode node4 = new TreeLinkNode(11);

        pNode.left = left;
        pNode.right=right;
        left.left = node1;
        left.right = node2;
        right.left = node3;
        right.right = node4;

        node1.next = left;
        node2.next= left;
        node3.next = right;
        node4.next = right;

        left.next = pNode;
        right.next = pNode;

        TreeLinkNode treeLinkNode = GetNext(pNode);
        System.out.println(treeLinkNode.val);
    }


    public static final List<TreeLinkNode> list = new ArrayList<>();
    public static TreeLinkNode GetNext(TreeLinkNode pNode) {
        int val = pNode.val;
        TreeLinkNode root = pNode;
        while (root.next != null) {
            root = root.next;
        }
        inOrder(root);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).val == val) {
                return i == list.size() - 1 ? null : list.get(i + 1);
            }
        }
        return null;
    }

    private static void inOrder(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root);
        inOrder(root.right);
    }

    /**
     * 找到下一个节点
     * 1. 找到根节点 root；
     * 2. 获取treeNode 所有的元素
     * 2. 找到比他大的所有val,排序、取最小值；
     * 3. 直接返回，或者通过最小值找到该结点；
     *
     * @param pNode 树
     * @return 指定节点的中序的下一个节点；
     * @apiNote 指定节点的值；
     * @author leiming5
     */
    public TreeLinkNode GetNextback(TreeLinkNode pNode, int val) {
        //
        Queue<TreeLinkNode> queue = new LinkedList<>();
        Queue<TreeLinkNode> other = new LinkedList<>();

        queue.offer(pNode);
        other.offer(pNode);

        List<Integer> values = new ArrayList<>();

        while (!queue.isEmpty()) {

            TreeLinkNode poll = queue.poll();
            values.add(poll.val);

            if (poll.left != null) {
                queue.offer(poll.left);
                other.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
                other.offer(poll.right);
            }
        }

        int nextVal = values.stream().filter(o -> o > val).mapToInt(o -> o).min().getAsInt();
        while (!other.isEmpty()) {
            TreeLinkNode poll = other.poll();
            if (poll.val == nextVal) {
                return poll;
            }
        }

        return null;
    }
}
