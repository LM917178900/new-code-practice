package com.leinovo.codepractice.hub1_20;

import java.util.Stack;

/**
 * @description: Solution5
 * @author: leiming5
 * @date: 2021/9/24 19:05
 */
public class Solution5 {
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
