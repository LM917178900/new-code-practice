package com.leinovo.codepractice.hub1_20;

/**
 * 有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，变成一个旋转数组，
 * 比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。请问，给定这样一个旋转数组，求数组中的最小值。
 * <p>
 * 数据范围：1≤n≤10000，数组中任意元素的值: 0≤val≤10000
 * 要求：空间复杂度：O(1) ，时间复杂度：O(logn)
 *
 * @description: Solution11
 * @author: leiming5
 * @date: 2021/10/21 14:37
 */
public class Solution11 {
    public static void main(String[] args) {

        int[] arr = {1, 0, 1, 1, 1};
        int result = minNumberInRotateArray(arr);
        System.out.println(result);
    }

    public static int minNumberInRotateArray(int[] array) {

        int start = 0;
        int end = array.length - 1;

        int result = getMin(start, end, array);
        return result;
    }

    private static int getMin(int start, int end, int[] array) {

        if (start + 1 == end && array[start] < array[end]) {
            return array[start];
        } else if (start + 1 == end && array[start] > array[end]) {
            return array[end];
        }

        int target = array[end];
        int mid = (start + end) / 2;

        if (array[mid] > target) {
            return getMin(mid, end, array);
        }
        if (array[mid] < target) {
            return getMin(start, mid, array);
        }
        if (array[mid] == target && array[start] == target && array[end] == target) {
            boolean rightAllTarget = true;
            for (int i = mid; i <= end; i++) {
                rightAllTarget = rightAllTarget && array[i] == target;
            }

            if (rightAllTarget) {
                return getMin(start, mid, array);
            } else {
                return getMin(mid, end, array);
            }
        } else if (array[mid] == target && array[start] == target) {
            return getMin(mid, end, array);
        } else if (array[mid] == target && array[end] == target) {
            return getMin(start, mid, array);
        }
        return -1;
    }

    public static int minNumberInRotateArrayback(int[] array) {
        int i = 0;
        int result = 0;
        while (true) {
            if (array[i] > array[i + 1]) {
                result = array[i + 1];
                break;
            }
            i++;

            if (i == array.length - 1) {
                result = array[i];
                break;
            }
        }
        return result;
    }
}
