package com.leinovo.codepractice.hub1_10;

import java.util.Stack;

/**
 * @description: Solution20
 * @author: leiming5
 * @date: 2021/10/11 20:04
 */
public class Solution20 {

    Stack<Integer> stackTotal = new Stack<Integer>();
    Stack<Integer> stackLittle = new Stack<Integer>();

    public void push(int node) {
        stackTotal.push(node);
        if(stackLittle.empty()){
            stackLittle.push(node);
        }else{
            if(node <= stackLittle.peek()){
                stackLittle.push(node);
            }else{
                stackLittle.push(stackLittle.peek());
            }
        }
    }

    public void pop() {
        stackTotal.pop();
        stackLittle.pop();
    }

    public int top() {
        return stackTotal.peek();
    }

    public int min() {
        return stackLittle.peek();
    }
}
