package com.leinovo.codepractice.hub1_20;


/**
 * @description: Solution4
 * @author: leiming5
 * @date: 2021/9/24 17:32
 */
public class Solution4 {
//    public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
//        if (pre.length == 0 || vin.length == 0) {
//            return null;
//        }
//
//        TreeNode root = new TreeNode(pre[0]);
//        for (int i = 0; i < vin.length; i++) {
//            if (pre[0] == vin[i]) {
//                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
//                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
//                break;
//            }
//        }
//        return root;
//    }
}