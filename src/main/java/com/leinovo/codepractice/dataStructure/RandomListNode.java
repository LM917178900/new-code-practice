package com.leinovo.codepractice.dataStructure;

/**
 * @description: RandomListNode
 * @author: leiming5
 * @date: 2021/11/18 16:32
 */
public class RandomListNode {

    public int label;
    public RandomListNode next = null;
    public RandomListNode random = null;

    public RandomListNode(int label) {
        this.label = label;
    }
}
