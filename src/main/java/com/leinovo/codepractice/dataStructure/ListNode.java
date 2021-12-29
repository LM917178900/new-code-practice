package com.leinovo.codepractice.dataStructure;

/**
 * @description: ListNode
 * @author: leiming5
 * @date: 2021/10/8 19:37
 */
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 打印输出 链表
     */
    public void printLnNode(ListNode p) {
        ListNode temp = p;
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

    public Integer getDeepOfListNode() {
        ListNode root = this;
        if (root.next == null) {
            return 1;
        }

        return root.next.getDeepOfListNode() + 1;
    }
}
