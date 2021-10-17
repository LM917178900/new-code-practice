package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.hub1_20.TreeNode;

import java.util.List;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则返回true,否则返回false。
 * 假设输入的数组的任意两个数字都互不相同。
 * （ps：我们约定空树不是二叉搜索树）
 *
 * @description: Solution23
 * @author: leiming5
 * @date: 2021/10/14 18:59
 */
public class Solution23 {

    public static void main(String[] args)throws Exception {
        int[] sequence = {4, 8, 6, 12, 16, 14, 10};
//        boolean b = VerifySquenceOfBST(sequence);
//        System.out.println(b);

        TreeNode result = postGenerate(sequence);
        System.out.println(result);
    }

    /**
     * 中序构建树；
     *
     * @param sequence
     * @return
     * @throws Exception
     */
    public  static TreeNode middleGenerate(int[] sequence) throws Exception {
        return null;
    }

    public  static TreeNode postGenerate(int[] sequence) throws Exception {

        int length = sequence.length;
        if(length==0){
            return null;
        }

        int val = sequence[length - 1];
        TreeNode root = new TreeNode(val);
        if(length==1){
            return root;
        }

        // key
        int leftLength = 0;
        for (int i = 0; i < length; i++) {
            if (val < sequence[i]) {
                break;
            }
            leftLength++;
        }

        // right 获取
        int rightLength = length - 1 - leftLength;
        if (rightLength > 0) {
            int[] right = new int[rightLength];
            for (int i = leftLength; i < length - 1; i++) {
                right[i - leftLength] = sequence[i];
                if (sequence[i] < val) {
                    throw  new Exception("");
                }
            }
            root.right = postGenerate(right);
        }

        // left 获取
        if (leftLength > 0) {
            int[] left = new int[leftLength];
            for (int i = 0; i < length - 1; i++) {
                if (val < sequence[i]) {
                    break;
                }
                left[i] = sequence[i];
            }

            root.left = postGenerate(left);
        }

        return root;
    }

    public static boolean VerifySquenceOfBST(int[] sequence) {

        int length = sequence.length;
        if (length ==0) {
            return false;
        }
        if (length ==1) {
            return true;
        }

        // 1. 获取根节点
        int val = sequence[length - 1];

        // 2. 获取左子树的所有节点的val;
        // 3. 获取右子树所有节点的val;
        // 4. 左右子树同时满足二叉查找树的定义；
        int leftLength = 0;
        for (int i = 0; i < length; i++) {
            if (val < sequence[i]) {
                break;
            }
            leftLength++;
        }

        boolean rightValidate = true;
        int rightLength = length - 1 - leftLength;
        if (rightLength > 1) {
            int[] right = new int[rightLength];
            for (int i = leftLength; i < length - 1; i++) {
                right[i - leftLength] = sequence[i];
                if (sequence[i] < val) {
                    return false;
                }
            }
            rightValidate = VerifySquenceOfBST(right);
        }

        boolean leftValidate = true;
        if (leftLength > 1) {
            int[] left = new int[leftLength];
            for (int i = 0; i < length - 1; i++) {
                if (val < sequence[i]) {
                    break;
                }
                left[i] = sequence[i];
            }
            leftValidate = VerifySquenceOfBST(left);
        }


        return leftValidate && rightValidate;
    }

    public static List<Integer> postTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return result;
        }
        postTraversal(root.left, result);
        postTraversal(root.right, result);
        result.add(root.val);
        return result;
    }

}
