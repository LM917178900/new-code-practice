package com.leinovo.codepractice.elseAlgorithm;

/**
 * 快速排序 复习
 *
 * @description: quickSort
 * @author: leiming5
 * @date: 2021/10/20 9:59
 */
public class quickSort {
    /**
     * 思路： 数组中获取第一个数，通过元素交换，将其他比它小的放在它的左边，比它大的放在它的右边；
     * 递归左右两个数组，实现排序；
     * <p>
     * 1. 数组第一个数作为参考值 ref，定义两个变量 i=，j=length-1;
     * 2. 然后 ref 于i,j 对应的元素进行比较交换，从j->i->j 交替进行
     * 3. 从而实现生成俩个数组；
     * 4. 遍历生产结果
     *
     * @param arr
     * @return
     */
    public static void main(String[] args) {
        int arr[] = new int[]{3, 3, 3, 7, 9, 122344, 4656, 34, 34, 4656, 5, 6, 7, 8, 9, 343, 57765, 23, 12321};
        int len = arr.length - 1;
        arr = sort(arr, 0, len);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    private static int[] sort(int[] arr, int start, int end) {

        int ref = arr[start];
        int i = start;
        int j = end;
        int temp;
        while (i < j) {
            while (i < j && arr[j] > ref) {
                j--;
            }
            while (i < j && arr[i] < ref) {
                i++;
            }
            // 因其中一方是ref, 所以，i/j只能移动一个；
            if(i < j && arr[i] == arr[j]){
//                i++;
                j--;
            }
            else if (i < j && arr[i] > arr[j]) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        if (start < i - 1) {
            sort(arr, start, i - 1);
        }
        if (i + 1 < end) {
            sort(arr, i + 1, end);
        }

        return arr;
    }

}
