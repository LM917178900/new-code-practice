package com.leinovo.codepractice.hub1_20;

import java.util.Stack;

/**
 * 1. 特性
 * stack:先进后出
 * list：先进先出
 * 2. 方法
 * 模拟过程，找出规律
 *
 * @description: Solution9_1
 * @author: leiming5
 * @date: 2021/10/20 9:44
 */
public class Solution9_1 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
