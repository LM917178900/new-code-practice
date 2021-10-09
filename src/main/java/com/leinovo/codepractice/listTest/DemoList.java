package com.leinovo.codepractice.listTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: DemoList
 * @author: leiming5
 * @date: 2021/9/23 9:52
 */
public class DemoList {

    public static void main(String[] args) {
        testOne();
    }
    // 1. void add(int index,E element)
    public  static void testOne(){
        List<String> list = new ArrayList<>();
        list.add("demo1");
        list.add("demo2");
        list.add("demo3");

        list.add(0,"3");
        list.add(0,"2");
        list.add(0,"1");

        list.add("1");
        list.add("2");

        System.out.println(list);

    }
}
