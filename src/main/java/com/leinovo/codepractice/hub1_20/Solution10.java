package com.leinovo.codepractice.hub1_20;

/**
 * @description: Solution10
 * @author: leiming5
 * @date: 2021/10/20 9:50
 */
public class Solution10 {
    public static void main(String[] args) {
        int fibonacci = Fibonacci(4);
        System.out.println(fibonacci);
    }

    public static int Fibonacci(int n) {
        if(n==1|| n==2){
            return 1;
        }else{
            return Fibonacci(n-1) + Fibonacci(n-2);
        }
    }
}
