package com.leinovo.codepractice.dataStructure;

/**
 * @description: ArrUtil
 * @author: leiming5
 * @date: 2021/11/8 15:37
 */
public class ArrUtil {
    /**
     * 截取；
     *
     * @param start
     * @param end
     * @param arr
     * @return
     */
    public static  char[] subString(int start, int end, char[] arr) {
        int length = end - start;
        char[] resultArr = new char[length];
        for (int i = start; i < end; i++) {
            resultArr[start - i] = arr[i];
        }
        return resultArr;
    }

    public static char[] subString(int start, char[] arr) {

        int end = arr.length;
        return subString(start, end, arr);
    }
}
