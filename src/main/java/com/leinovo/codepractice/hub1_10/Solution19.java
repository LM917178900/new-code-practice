package com.leinovo.codepractice.hub1_10;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 * [[1,2,3,4],
 * [5,6,7,8],
 * [9,10,11,12],
 * [13,14,15,16]]
 * 则依次打印出数字
 * [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
 *
 * @description: Solution19
 * @author: leiming5
 * @date: 2021/10/10 16:35
 */
public class Solution19 {
    public static void main(String[] args) {
        int[][] matrix = {{1}, {2}, {3}, {4}, {5}};
        ArrayList<Integer> arr = printMatrix(matrix);
        System.out.println(arr.toArray().toString());
    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {

        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        ArrayList<Integer> result = new ArrayList<>();

        int row = 0;
        int col = 0;
        while (true) {

            // 遍历上面一行
            for (col = colStart; col <= colEnd; col++) {
                result.add(matrix[rowStart][col]);
            }
            rowStart++;
            if (rowStart > rowEnd) {
                break;
            }

            // 遍历右边一行
            for (row = rowStart; row <= rowEnd; row++) {
                result.add(matrix[row][colEnd]);
            }
            colEnd--;
            if (colEnd < colStart) {
                break;
            }

            // 遍历下边一行
            for (col = colEnd; col >= colStart; col--) {
                result.add(matrix[rowEnd][col]);
            }
            rowEnd--;
            if (rowEnd < rowStart) {
                break;
            }

            //
            for (row = rowEnd; row >= rowStart; row--) {
                result.add(matrix[row][colStart]);
            }
            colStart++;
            if (colStart > colEnd) {
                break;
            }
        }
        return result;
    }


    public static ArrayList<Integer> printMatrixBack(int[][] matrix) {

        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        ArrayList<Integer> result = new ArrayList<>();

        int row = 0;
        int col = 0;
        int count = (1 + rowEnd) * (1 + colEnd);
        int i = 0;

        // 定义：初始运行方向
        int orientation = colEnd == 0 ? 2 : 1;

        while (i < count) {
            int item = matrix[row][col];
            result.add(item);
            i++;

            // 向右运动
            if (orientation == 1 && row == rowStart && col < colEnd) {
                col++;
                // 右下拐
                if (col == colEnd) {
                    rowStart += 1;
                    orientation = 2;
                }
                continue;
            }

            // 向下运动
            if (orientation == 2 && col == colEnd && row < rowEnd) {
                row++;
                // 下左拐
                if (row == rowEnd) {
                    colEnd -= 1;
                    orientation = 3;
                }
                continue;
            }

            // 向左运动
            if (orientation == 3 && row == rowEnd && col > colStart) {
                col--;
                // 左上拐
                if (col == colStart) {
                    rowEnd -= 1;
                    orientation = 4;
                }
                continue;
            }

            // 向上运动
            if (orientation == 4 && col == colStart && row > rowStart) {
                row--;
                // 上右拐
                if (row == rowStart) {
                    colStart += 1;
                    orientation = 1;
                }
            }
        }
        return result;
    }
}
