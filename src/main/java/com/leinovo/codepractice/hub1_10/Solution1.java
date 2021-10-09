package com.leinovo.codepractice.hub1_10;

import java.util.Vector;

/**
 * https://www.nowcoder.com/ta/coding-interviews
 * 第一题
 *
 * @description: Solution1
 * @author: leiming5
 * @date: 2021/9/23 9:21
 */
public class Solution1 {
    /**
     * 描述
     * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * [
     * [1,2,8,9],
     * [2,4,9,12],
     * [4,7,10,13],
     * [6,8,11,15]
     * ]
     * 给定 target = 7，返回 true。
     *
     * 给定 target = 3，返回 false。
     *
     * 0 <= array.length <= 500
     * 0 <= array[0].length <= 500
     *
     * 你能给出时间复杂度为  的解法吗？（n,m为矩阵的长和宽）
     * @param target
     * @param array
     * @return
     */
    public static boolean find(int target,int[][] array){
        int rows = array.length;
        int cols = array[0].length;

        // 从最后一行、第一列开始；
        int row=rows-1;
        int col=0;

        while (row>=0 && col<=cols-1){

            // 第一个元素不满足，整行都会大于target,换一行；
            // 中间发现元素 大于target, 减一行，元素的直会减少，进行比较
            if(array[row][col]>target){
                row--;
            }
            // 该行部分元素小于target; 遍历下一个元素；
            else if(array[row][col]<target){
                col++;
            }else {
                return true;
            }
        }
        return false;
    }

    public  static boolean find(int target, Vector<Vector<Integer>> array) {
        for(int i=0;i<array.size();i++){
            Vector<Integer> item= array.get(i);
            int m = item.size();
            if(item.get(0)==target){
                return true;
            }
            if(item.get(m-1)<target){
                continue;
            }
            if(item.get(0)>target){
                continue;
            }
            for(int j=0;j<item.size();j++){
                if(item.get(j)==target){
                    return true;
                }
            }
        }
        return false;
    }
}
