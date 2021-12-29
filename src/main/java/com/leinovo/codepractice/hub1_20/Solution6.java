package com.leinovo.codepractice.hub1_20;

import com.leinovo.codepractice.dataStructure.ListNode;

import java.util.ArrayList;

/**
 * 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 *
 * @description: Solution6
 * @author: leiming5
 * @date: 2021/10/15 9:02
 */
public class Solution6 {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> result = new ArrayList<>();
        ListNode temp = listNode;

        while(temp!=null){
            result.add(0,temp.val);
            temp = temp.next;
        }
        return result;
    }
}
