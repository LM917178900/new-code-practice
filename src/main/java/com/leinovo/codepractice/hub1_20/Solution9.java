package com.leinovo.codepractice.hub1_20;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。
 * 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 *
 * 数据范围： n\le1000n≤1000
 * 要求：存储n个元素的空间复杂度为 O(n)O(n) ，插入与删除的时间复杂度都是 O(1)O(1)
 *
 * @description: Solution9
 * @author: leiming5
 * @date: 2021/10/19 19:46
 */
public class Solution9 {
    /**
     *  1. 插入，stack1 插入元素
     *  2. 弹出，stack2 接收 stack1 pop 的元素，pop stack2 顶部元素;
     *  3. 插入1个，stack1 栈顶插入，->弹出一个，stack2 有值，pop stack1;
     *  4. 插入多个，stack1 栈顶插入，-》弹出一个，将stack1所有元素弹出到stack2;
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    int size;

    public void push(int node) {
        stack1.push(node);
        size++;
    }

    public int pop() {

        // 出栈，缓存到 另一个栈；
        for(int i=0;i<size-1;i++){
            int temp=stack1.pop();
            stack2.push(temp);
        }
        int button= stack1.pop();
        size--;

        // 入栈
        for(int i=0;i<size;i++){
            int temp=stack2.pop();
            stack1.push(temp);
        }

        return button;

    }

}
