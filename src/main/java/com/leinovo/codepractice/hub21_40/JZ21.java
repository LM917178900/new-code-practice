package com.leinovo.codepractice.hub21_40;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个长度为 n 整数数组，数组里面不含有相同的元素，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前面部分，所有的偶数位于数组的后面部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * <p>
 * 数据范围：0 \le n \le 50000≤n≤5000，数组中每个数的值 0 \le val \le 100000≤val≤10000
 * 要求：时间复杂度 O(n)O(n)，空间复杂度 O(n)O(n)
 * 进阶：时间复杂度 O(n^2)O(n
 * 2
 * )，空间复杂度 O(1)O(1)
 *
 * @description: JZ21
 * @author: leiming5
 * @date: 2021/11/16 9:58
 */
public class JZ21 {

    public static void main(String[] args) {
        int[] array={1,2,3,4};
        int[] ints = reOrderArray(array);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
    /**
     * 运输组移位法
     * 1. 定义两个变量，当前奇数下标i,i==0表示没有奇数；遍历数组下标j;
     * 2. 遍历数组，遇到偶数，j++
     * 3. 遇到奇数，取出，然后，i到j 之间的数批量后移一位，将奇数放到i位置；
     * 4. j==length-1 遍历结束；
     *
     * @param array
     * @return
     */
    public static int[] reOrderArray(int[] array) {

        int i = 0;
        for (int j = 0; j < array.length; j++) {
            int item = array[j];
            if (item % 2 == 1) {
                move(array, i, j);
                i++;
            }
        }
        return array;
    }

    private static void move(int[] array, int i, int j) {
        int item = array[j];
        for (int k = j; k > i; k--) {
            array[k] = array[k - 1];
        }
        array[i] = item;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] reOrderArrayBack(int[] array) {
        // write code here
        int length = array.length;

        List<Integer> listOdd = new ArrayList<>();
        List<Integer> listEven = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int item = array[i];
            if (item % 2 == 1) {
                listOdd.add(item);
            } else {
                listEven.add(item);
            }
        }
        listOdd.addAll(listEven);

        int[] newArr = new int[length];
        for (int i = 0; i < length; i++) {
            newArr[i] = listOdd.get(i);
        }

        return newArr;
    }
}
