package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 * <p>
 * 数据范围： n\le10000n≤10000
 * 节点值范围：[1,10000]
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 * 例如，输入{1,2},{3,4,5}时，对应的环形链表如下图所示：
 *
 * @description: JZ23
 * @author: leiming5
 * @date: 2021/11/16 16:11
 */
public class JZ23 {

    /**
     * 1. 遍历链表
     * 2. 遍历中封装遍历过的数据；
     * 3. 同时把当前节点到遍历过的链表中取对比；
     * 4. 发现相等的节点，返回当前节点；
     * 5. 没有发现，继续遍历；
     *
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {

        List<ListNode> record = new ArrayList<>();
        while (true) {
            if(pHead==null){
                return null;
            }
            record.add(pHead);
            pHead = pHead.next;
            if (record.contains(pHead)) {
                break;
            }
        }

        return pHead;
    }

}
