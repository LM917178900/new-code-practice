package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.hub1_20.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 遍历 二叉树的两种方法
 *
 *  1. 广度遍历:层次遍历：从上到下，从左到右。
 *  2. 深度遍历：
 *  2.1 前序遍历：根结点 ---> 左子树 ---> 右子树
 *  2.2 中序遍历：左子树---> 根结点 ---> 右子树
 *  2.3 后序遍历：左子树 ---> 右子树 ---> 根结点
 *
 * @description: TreeNodeTraversal
 * @author: leiming5
 * @date: 2021/10/14 9:24
 */
public class TreeNodeTraversal {

    /**
     *  1. 广度遍历:层次遍历：从上到下，从左到右。
     */
    public void levelTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.println(poll.val);

            if(poll.left !=null){
                queue.offer(poll.left);
            }
            if(poll.right !=null){
                queue.offer(poll.right);
            }
        }

    }
    /**
     *  2.1 前序遍历：根结点 ---> 左子树 ---> 右子树
     */
    public void preTraversal(TreeNode root){

        if(root == null){
            return;
        }
        System.out.println(root.val);

        preTraversal(root.left);
        preTraversal(root.right);
    }
    /**
     *  2.2 中序遍历：左子树---> 根结点 ---> 右子树
     */
    public void inTraversal(TreeNode root){
        if(root == null){
            return;
        }
        preTraversal(root.left);
        System.out.println(root.val);
        preTraversal(root.right);
    }
    /**
     *  2.3 后序遍历：左子树 ---> 右子树 ---> 根结点
     */
    public void postTraversal(TreeNode root){
        if(root == null){
            return;
        }
        preTraversal(root.left);
        preTraversal(root.right);
        System.out.println(root.val);
    }
}
