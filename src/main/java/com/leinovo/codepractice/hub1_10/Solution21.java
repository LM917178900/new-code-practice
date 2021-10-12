package com.leinovo.codepractice.hub1_10;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 *
 * @description: Solution21
 * @author: leiming5
 * @date: 2021/10/12 11:31
 */
public class Solution21 {
    public static void main(String[] args) {
        int[] pushA ={1,2,3,4,5};
        int[] popA ={4,5,3,2,1};

        boolean result = IsPopOrder(pushA, popA);
        System.out.println(result);
    }
    /**
     * 对入栈序列进行入栈的模拟，
     * 然后在模拟的过程当中，判断栈顶元素和出栈序列的相等关系，
     * 从而判断出对栈顶元素的操作。
     *
     * @param pushA 入栈数据
     * @param popA 出栈数据
     * @return
     */
    public static  boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0; // 入栈序列的下标
        int popIndex = 0; // 出栈序列的下标

        // 加入过程中pop 的数据
        while (pushIndex < pushA.length) {

            // 栈顶元素等于 记录数组的第一个元素
            if (!stack.isEmpty() && stack.peek() == popA[popIndex]) {
                // 遍历栈的下一个元素，与数组的第二个元素比较；
                stack.pop();
                popIndex++;
            } else {
                // 如果展示空的，或者，栈顶元素与 数组对应的元素不相等；
                // 加入push 数组的一个新元素到栈中；
                stack.push(pushA[pushIndex]);
                pushIndex++;
            }
        }

        // 下面的这个while循环其实就是为了防止当所有入栈的元素都压入栈的时候，栈顶元素和出栈序列的下标所指的数字没有来得及比较。
        while (!stack.isEmpty()) {
            if (stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                return false;
            }
        }
        return true;
    }



    public boolean IsPopOrderback(int[] pushA, int[] popA) {

        if (pushA.length != popA.length) {
            return false;
        }

        boolean flag = true;
        int length = pushA.length;
        for (int i = 0; i < length; i++) {
            flag = flag && (pushA[i] == popA[length - 1 - i]);
        }
        return flag;
    }
}
