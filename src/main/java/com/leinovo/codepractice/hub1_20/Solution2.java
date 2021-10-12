package com.leinovo.codepractice.hub1_20;

/**
 * @description: ss
 * @author: leiming5
 * @date: 2021/9/24 16:15
 */
public class Solution2 {
    public static void main(String[] args) {
        String we_are_here = replaceSpace("We Are Here");
        System.out.println(we_are_here);
    }
    /**
     * http://ascii.911cha.com/
     *
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return string字符串
     */
    public  static  String replaceSpace (String s) {
        // write code here
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]==32){
                stringBuilder.append("20%");
            }else{
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();
    }

    public String replaceSpaceBack (String s) {
        // write code here
        return s.replace(" ","%20");
    }
}
