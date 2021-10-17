package com.leinovo.codepractice.hub1_20;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定节点数为 n 二叉树的前序遍历和中序遍历结果，
 * 请重建出该二叉树并返回它的头结点。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
 *
 * @description: Solution7
 * @author: leiming5
 * @date: 2021/10/15 9:09
 */
public class Solution7 {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] vin = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode result = reConstructBinaryTree(pre, vin);
        System.out.println(result);
    }

    /**
     * 1.通过前序节点找到根节点
     * 2.通过根节点在中序中找到前子树和后子树的 元素；
     * 3.左子树和右子树的元素分别进行递归
     *
     * @param pre
     * @param vin
     * @return
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] vin) {

        if ((pre == null || pre.length == 0) && (vin == null || vin.length == 0)) {
            return null;
        }

        int val = pre[0];
        int key = 0;
        int vinLength = vin.length;
        for (int i = 0; i < vinLength; i++) {
            if (val == vin[i]) {
                key = i;
                break;
            }
        }

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        int[] leftVin = new int[key];
        int[] rightVin = new int[vinLength - key - 1];
        for (int i = 0; i < vinLength; i++) {
            if (i < key) {
                leftList.add(vin[i]);
                leftVin[i] = vin[i];
            }
            if (key < i) {
                rightList.add(vin[i]);
                rightVin[i - key - 1] = vin[i];
            }
        }
        int[] leftPre = new int[leftVin.length];
        int leftCount = 0;
        int[] rightPre = new int[rightVin.length];
        int rightCount = 0;
        int preLength = pre.length;
        for (int i = 0; i < preLength; i++) {
            if (leftList.contains(pre[i])) {
                leftPre[leftCount] = pre[i];
                leftCount++;
            } else if (rightList.contains(pre[i])) {
                rightPre[rightCount] = pre[i];
                rightCount++;
            }
        }

        TreeNode node = new TreeNode(val);
        TreeNode left = reConstructBinaryTree(leftPre, leftVin);
        TreeNode right = reConstructBinaryTree(rightPre, rightVin);
        node.left = left;
        node.right = right;
        return node;
    }

    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return null;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

}
