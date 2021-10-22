package com.leinovo.codepractice.hub1_20;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: Solution12
 * @author: leiming5
 * @date: 2021/10/21 16:45
 */
public class Solution12 {
    public static void main(String[] args) {
        char[][] matrix = {{'a', 'b','c','e'}, {'s','f','c','s'},{'a', 'd','e','e'}};
        boolean result = hasPath(matrix, "abcb");
        System.out.println(result);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param matrix char字符型二维数组
     * @param word string字符串
     * @return bool布尔型
     */
    public static List<Integer> recordRows = new ArrayList<>();
    public static List<Integer> recordCols = new ArrayList<>();

    public static boolean hasPath(char[][] matrix, String word) {

        char[] words = word.toCharArray();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (String.valueOf(matrix[i][j]).equals(word.substring(0, 1))) {
                    if (dfs(matrix, words, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] matrix, char[] word, int i, int j, int index) {

        //边界的判断，如果越界直接返回false。index表示的是查找到字符串word的第几个字符，
        //如果这个字符不等于matrix[i][j]，说明验证这个坐标路径是走不通的，直接返回false
        if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0 || matrix[i][j] != word[index])
            return false;
        //如果word的每个字符都查找完了，直接返回true
        if (index == word.length - 1)
            return true;

        //把当前坐标的值保存下来，为了在最后复原
        char tmp = matrix[i][j];
        //然后修改当前坐标的值,防止二次使用，也避免了记录已经走过的路线
        matrix[i][j] = '.';
        //走递归，沿着当前坐标的上下左右4个方向查找
        boolean res = dfs(matrix, word, i + 1, j, index + 1)
                || dfs(matrix, word, i - 1, j, index + 1)
                || dfs(matrix, word, i, j + 1, index + 1)
                || dfs(matrix, word, i, j - 1, index + 1);

        // 递归之后再把当前的坐标复原
        matrix[i][j] = tmp;
        return res;
    }

    public static boolean hasPathBack(char[][] matrix, String word) {
        // write code here
        if ("".equals(word)) {
            return true;
        }

        int maxRow = matrix.length - 1;
        int maxCol = matrix[0].length - 1;
        if (recordRows.size() == 0) {
            /**
             *  挨个找
             */
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (String.valueOf(matrix[i][j]).equals(word.substring(0, 1))) {
                        recordRows.add(i);
                        recordCols.add(j);
                        boolean result = hasPath(matrix, word.substring(1));
                        if (result) {
                            return true;
                        } else {
                            recordRows.clear();
                            recordCols.clear();
                        }
                    }
                }
            }

        } else {
            int rowStart = recordRows.get(recordRows.size() - 1);
            int columnStart = recordCols.get(recordCols.size() - 1);
            String item = word.substring(0, 1);
            List<Integer> backRows = new ArrayList<>();
            back(backRows, recordRows);
            List<Integer> backCols = new ArrayList<>();
            back(backCols, recordCols);
            // 向右
            if (columnStart + 1 <= maxCol && String.valueOf(matrix[rowStart][columnStart + 1]).equals(item) && nonExist(rowStart, columnStart + 1)) {
                recordRows.add(rowStart);
                recordCols.add(columnStart + 1);
                boolean down = hasPath(matrix, word.substring(1));
                if (down) {
                    return true;
                } else {
                    recordRows.clear();
                    recordCols.clear();
                    back(recordRows, backRows);
                    back(recordCols, backCols);
                }
            }
            // 向左
            if (columnStart - 1 >= 0 && String.valueOf(matrix[rowStart][columnStart - 1]).equals(item) && nonExist(rowStart, columnStart - 1)) {
                recordRows.add(rowStart);
                recordCols.add(columnStart - 1);
                boolean down = hasPath(matrix, word.substring(1));
                if (down) {
                    return true;
                } else {
                    recordRows.clear();
                    recordCols.clear();
                    back(recordRows, backRows);
                    back(recordCols, backCols);
                }
            }
            // 向上
            if (rowStart - 1 >= 0 && String.valueOf(matrix[rowStart - 1][columnStart]).equals(item) && nonExist(rowStart - 1, columnStart)) {
                recordRows.add(rowStart - 1);
                recordCols.add(columnStart);
                boolean down = hasPath(matrix, word.substring(1));
                if (down) {
                    return true;
                } else {
                    recordRows.clear();
                    recordCols.clear();
                    back(recordRows, backRows);
                    back(recordCols, backCols);
                }
            }
            // 向下
            if (rowStart + 1 <= maxRow && String.valueOf(matrix[rowStart + 1][columnStart]).equals(item) && nonExist(rowStart + 1, columnStart)) {
                recordRows.add(rowStart + 1);
                recordCols.add(columnStart);
                boolean down = hasPath(matrix, word.substring(1));
                if (down) {
                    return true;
                } else {
                    recordRows.clear();
                    back(recordRows, backRows);
                    recordCols.clear();
                    back(recordCols, backCols);
                }
            }
        }

        return false;
    }

    private static void back(List<Integer> recordRows, List<Integer> backRows) {
        for (int i = 0; i < backRows.size(); i++) {
            recordRows.add(backRows.get(i));
        }
    }


    private static boolean nonExist(int row, int col) {

        boolean result = false;

        for (int i = 0; i < recordRows.size(); i++) {
            if (recordRows.get(i) == row) {
                result = result || recordCols.get(i) == col;
            }
        }
        return !result;
    }
}
