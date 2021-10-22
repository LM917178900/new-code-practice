package com.leinovo.codepractice.hub1_20;

/**
 * 描述
 * 地上有一个rows行和cols列的方格。坐标从 [0,0] 到 [rows-1,cols-1]。
 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于threshold的格子。
 * 例如，当threshold为18时，机器人能够进入方格[35,37]，因为3+5+3+7 = 18。
 * 但是，它不能进入方格[35,38]，因为3+5+3+8 = 19。
 * 请问该机器人能够达到多少个格子？
 *
 * 范围:
 * 1 <= rows, cols<= 100
 * 0 <= threshold <= 20
 *
 * @description: Solution13
 * @author: leiming5
 * @date: 2021/10/22 13:34
 */
public class Solution13 {
    /**
     *  1. 初次验证，如果rows/cols 满足返回(rows+1)*(cols+1)
     *  2. 如果满足，开始减少列，直到满足，从这个点开始遍历；
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {

        return 0;
    }



}
