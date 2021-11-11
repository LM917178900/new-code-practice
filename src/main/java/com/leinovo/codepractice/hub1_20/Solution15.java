package com.leinovo.codepractice.hub1_20;

/**
 * JZ15 二进制中1的个数
 *
 * @description: Solution15
 * @author: leiming5
 * @date: 2021/10/8 19:28
 */
public class Solution15 {

    public static ListNode ReverseList(ListNode head) {

        ListNode cur = head;
        ListNode result = null;

        int i = 0;
        while (cur != null) {
            if (i == 0) {
                result = new ListNode(cur.val);
            } else {
                // 1. 新建当前节点；
                // 2. 获取当前的值，
                // 3. 把前面的结果作为子节点；
                ListNode temp = new ListNode(cur.val);
                temp.next = result;
                result = temp;
            }
            cur = cur.next;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {

//        int[] arrA = {1, 2, 3};
//        ListNode headA = generateNode(arrA);
//
//        int[] arrB = {-1, -2, 3, 4};
//        ListNode headB = generateNode(arrB);
//
//        ListNode headC = Solution16.Merge(headA, headB);

//        ListNode result = ReverseList( head);
//        printLnNode(headC);

        int result = NumberOf1back2(-10);
        System.out.println(result);
    }

    /**
     * 打印输出 链表
     *
     * @param head
     * @return
     */
    public static void printLnNode(ListNode head) {

        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    /**
     * 生成 链表
     *
     * @param arr
     * @return
     */
    public static ListNode generateNode(int[] arr) {

        ListNode temp = null;
        ListNode result = null;

        // 1. 获取当前数据 val
        // 2. 获取当前节点 node
        // 3. 绑定上一个节点之间的关系；
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                result = new ListNode(arr[i]);
                temp = result;
            } else {
                ListNode current = new ListNode(arr[i]);
                temp.next = current;
                temp = current;
            }
        }
        return result;
    }

    /**
     * 描述
     * 输入一个整数 n ，输出该数32位二进制表示中1的个数。其中负数用补码表示。
     * <p>
     * 数据范围：- 2^{31} <= n <= 2^{31}-1
     * 即范围为:-2147483648<= n <= 2147483647
     * 示例1
     * 输入：10
     * 返回值：2
     *
     * @param n
     * @return
     */
    public static int NumberOf1(int n) {
        //
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static int NumberOf1back2(int n) {
        //
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (n >> i) & 1;
        }
        return count;
    }


    public static int NumberOf1back(int n) {

        boolean isNegative = n < 0;
        if (isNegative) {
            n = -n - 1;
        }

        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n / 2;
        }

        return isNegative ? (32 - count) : count;
    }
}
