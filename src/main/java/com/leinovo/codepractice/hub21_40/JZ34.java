package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 输入一颗二叉树的根节点root和一个整数expectNumber，找出二叉树中结点值的和为expectNumber的所有路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 * <p>
 * 如二叉树root为{10,5,12,4,7},expectNumber为22
 *
 * @description: JZ34
 * @author: leiming5
 * @date: 2021/11/17 11:53
 */
public class JZ34 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

//        ArrayList<ArrayList<Integer>> xx = FindPath(root, 22);
        System.out.println("xx=");

    }

    ArrayList res = new ArrayList<>();
    LinkedList path = new LinkedList<>();
    public ArrayList FindPath(TreeNode root,int target) {
        dfs(root, target);
        return res;
    }

    public void dfs(TreeNode root,int tar){

        if(root == null){
            return;
        }
        //将root节点放入路径集合
        path.add(root.val);
        //更新目标值，每放入一个节点，目标值应该相应减去对应节点的值，直到目标值为0
        tar -= root.val;
        //如果目标值减到了0 && 左节点为空 && 右节点为空 证明树已遍历完，此路径为目标路径
        if(tar==0 && root.left == null && root.right == null){
            res.add(new ArrayList(path));
        }
        // 递归左右子树
        dfs(root.left, tar);
        dfs(root.right, tar);
        //删除当前节点，在回溯过程中，此节点不在新路径上
        path.removeLast();
    }

    /**
     * 1. 找出多有路径；
     * 2. 每条路径求和
     * 3. 找出求和一致的路径
     *
     * @param root
     * @param expectNumber
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindPathBack(TreeNode root, int expectNumber) {

        ArrayList< ArrayList<Integer>> allRoutes = new ArrayList<>();

        String route = "";
        getRoute(root, route, allRoutes);

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        allRoutes.forEach(o -> {
            if (o.stream().mapToInt(i->i).sum() == expectNumber) {
                result.add(o);
            }
        });

        return result;
    }

    private static void getRoute(TreeNode root, String route, ArrayList< ArrayList<Integer>> allRoutes) {
        if (root == null) {
            return;
        }
        route += root.val;

        // 子节点处理；
        if (root.left == null && root.right == null) {

            ArrayList<Integer> road = new ArrayList<>();
            String[] split = route.split(",");
            for (int i = 0; i < split.length; i++) {
                int item = Integer.parseInt(split[i]);
                road.add(item);
            }
            allRoutes.add( road);
            return;
        }

        route += ",";

        if (root.left != null) {
            getRoute(root.left, route, allRoutes);
        }

        if (root.right != null) {
            getRoute(root.right, route, allRoutes);
        }
    }
}
