package com.leinovo.codepractice.hub1_20;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 地上有一个rows行和cols列的方格。坐标从 [0,0] 到 [rows-1,cols-1]。
 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于threshold的格子。
 * 例如，当threshold为18时，机器人能够进入方格[35,37]，因为3+5+3+7 = 18。
 * 但是，它不能进入方格[35,38]，因为3+5+3+8 = 19。
 * 请问该机器人能够达到多少个格子？
 * <p>
 * 范围:
 * 1 <= rows, cols<= 100
 * 0 <= threshold <= 20
 *
 * @description: Solution13
 * @author: leiming5
 * @date: 2021/10/22 13:34
 */
public class Solution13 {

    public static void main(String[] args) {

        int result = movingCount(15, 20, 20);
        System.out.println(result);
    }

    public static int movingCount(int threshold, int rows, int cols) {
        List<String> allList = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 16 && j == 19) {
                    System.out.println("stop here");
                }
                int row = getCount(i);
                int col = getCount(j);

                if (row + col <= threshold) {
                    boolean routeTest = allList.size() == 0 || allList.contains((i - 1) + "-" + j) || allList.contains(i + "-" + (j - 1));
                    if (routeTest) {
                        allList.add(i + "-" + j);
                    }
                }
            }
        }

        return allList.size();
    }

    private static int getCount(int number) {
        String row = String.valueOf(number);
        int result = 0;
        char[] chars = row.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            result += Integer.parseInt(String.valueOf(chars[i]));
        }
        return result;
    }


}
