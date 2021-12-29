package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.ListNode;

import java.util.Stack;

/**
 * @description: JZ22
 * @author: leiming5
 * @date: 2021/11/16 11:08
 */
public class JZ22 {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode level2 = new ListNode(2);
        ListNode level3 = new ListNode(3);
        ListNode level4 = new ListNode(4);
        ListNode level5 = new ListNode(5);

        root.next = level2;
        level2.next = level3;
        level3.next = level4;
        level4.next = level5;

        ListNode xx = FindKthToTail(root, 2);
        System.out.println(xx);

    }

    /**
     * 使用压栈，出栈实现
     * 1. 压栈；
     * 2. 出栈；栈深度小于k;结束；
     *
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode pHead, int k) {
        if (pHead == null || k == 0) {
            return null;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode temp = pHead;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        if (stack.size() < k) {
            return null;
        }

        ListNode result = stack.pop();
        while (--k > 0) {
            result = stack.pop();
        }
        return result;
    }

    /**
     * 1. 获取 ListNode 的深度
     * 2. 获取指定k 的深度deepK；
     * 3. deepK 解析
     *
     * @param pHead ListNode类
     * @param k     int整型
     * @return ListNode类
     */
    public static ListNode FindKthToTailBack(ListNode pHead, int k) {
        // write code here
        if (pHead == null) {
            return null;
        }

        Integer deep = getDeepOfListNode(pHead);
        int kDeep = deep - k;
        if (kDeep < 0) {
            return null;
        }

        int record = 0;
        ListNode temp = pHead;
        while (temp != null) {
            if (record == kDeep) {
                break;
            }
            record++;
            temp = temp.next;
        }
        return temp;
    }

    public static Integer getDeepOfListNode(ListNode root) {
        if (root.next == null) {
            return 1;
        }

        return getDeepOfListNode(root.next) + 1;
    }
}
