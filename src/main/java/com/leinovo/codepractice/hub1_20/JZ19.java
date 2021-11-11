package com.leinovo.codepractice.hub1_20;

/**
 * 描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，
 * 但是与"aa.a"和"ab*a"均不匹配
 * <p>
 * 数据范围:
 * 1.str 可能为空，且只包含从 a-z 的小写字母。
 * 2.pattern 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 3.1 <= str.length <= 20
 * 4.1 <= pattern.length <= 30
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 *
 * @description: JZ19
 * @author: leiming5
 * @date: 2021/10/27 20:19
 * str =   "a"  "aaa" "a"   "ab"    "a"       "a"
 * pattern ".*" ".*a" "a."  ".*.."  "ab*a"     ".*a"
 * result   t     v     v     v                 v
 */
public class JZ19 {
    public static void main(String[] args) {
        String str = "ab";
        String pattern = ".*..";
//        boolean match = match(str, pattern);
        boolean match = charMatch(str, pattern);

        System.out.println(match);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str     string字符串
     * @param pattern string字符串
     * @return bool布尔型
     */
    public static boolean match(String str, String pattern) {
        // write code here
        char[] pChars = pattern.toCharArray();
        char[] sChars = str.toCharArray();

        return charMatch(pChars, sChars);
    }

    /**
     * 递归
     * 假设主串为 str，模式串为 pattern 从最后一步出发，需要关注最后进来的字符。假设 str 的长度为 n ，pattern的长度为 m ，
     * 关注正则表达式 pattern的最后一个字符是谁，它有三种可能，正常字符、'*' 和 '.'（点），那针对这三种情况讨论即可，如下：
     * <p>
     * 1. 判断是否是普通字符，str[n-1]==pattern[m-1];true,str[n-2]==pattern[m-2],false 结束；
     * 2. pattern[m-1]=='.';str[n-2]==pattern[m-2]
     * 3. pattern[m-1]=='*';str[n-2]==pattern[m-2]
     * 3.1 pattern[m-2]=='C' && str[n-1]='C' 匹配多个；
     * 3.2 pattern[m-2]=='C' && str[n-1]！='C' 匹配0个；
     *
     * @param str
     * @param pattern
     * @return
     */
    private static boolean charMatchBack(String str, String pattern) {

        int n = str.length();
        int m = pattern.length();

        boolean[][] f = new boolean[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 字符串为空，
                if (j == 0) {
                    f[i][j] = i == 0;
                    // 匹配项为空，字符串有值，匹配结果都为 false;
                } else {
                    // 当前模式不为*
                    if (pattern.charAt(j - 1) != '*') {
                        if (i > 0 && (pattern.charAt(j - 1) == '.' || str.charAt(i - 1) == pattern.charAt(j - 1))) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        // 当前模式为*; n最少为0 能够匹配；
                        if (j > 1) {
                            f[i][j] = f[i][j - 2];
                        }
                        // 前一个模式，能匹配当前字符，当前匹配结果于前面俩个字符匹配结果一致;
                        // 可能匹配多个，n>1,模式继续保留
                        if (i > 0 && j > 1 && (pattern.charAt(j - 2) == '.' || str.charAt(i - 1) == pattern.charAt(j - 2))) {
                            f[i][j] = f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    private static boolean charMatch(String str, String pattern) {

        // write code here
        int n = str.length();
        int m = pattern.length();

        // 期间每一种匹配的组合情况，总共有(n+1)*(m+1)总组合；最后一个组合为我们需要的组合；
        // 初始，将所有的匹配情况都设置为 false;
        boolean[][] f = new boolean[n + 1][m + 1];

        // 以字符串去匹配每个匹配项；
        // i,j=0; 代表空字符串；
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    // 除了都为空，返回true;
                    f[i][j] = i == 0;
                    // 匹配项为空，字符串有值，匹配结果都为 false;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (pattern.charAt(j - 1) != '*') {
                        // 字符串相等，或模式字符串='.'，返回(上一次的匹配结果);
                        if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                        // 其他情况默认为false;

                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            // 不管是匹配字符的数量n=0,最少和模式-2的匹配结果一致
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        // 当前匹配字符为*，且，前一个字符和匹配字符匹配，即，当前匹配为true，字符串于前一个匹配模式匹配一致；
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    private static boolean charMatch(char[] pChars, char[] sChars) {
        int pIndex = 0;
        int sIndex = 0;
        if (sChars.length > 0 && pChars.length == 0) {
            return false;
        }

        if (sChars.length == 0) {
            // 23
            if (pChars.length == 0) {
                return true;
            } else {
                while (pIndex < pChars.length) {
                    if (pIndex + 1 < pChars.length && pChars[pIndex + 1] == '*') {
                        pIndex += 2;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else {
            while (sIndex < sChars.length && pIndex < pChars.length) {
                if (pChars[pIndex] == sChars[sIndex]) {
                    sIndex++;
                    pIndex++;
                } else {
                    if (pChars[pIndex] == '.') { // 任意字符串
                        sIndex++;
                        pIndex++;
                        continue;
                    }
                    if (pChars[pIndex - 1] == sChars[sIndex] && pChars[pIndex] == '*') { // 多个字符串
                        pIndex++;
                        sIndex++;
                        continue;
                    }
                    if (pIndex > 0 && (pChars[pIndex - 1] == sChars[sIndex] || pChars[pIndex - 1] == '.') && pChars[pIndex] == '*') { // 多个字符串
                        sIndex++;
                        continue;
                    }
                    if (pIndex > 0 && (pChars[pIndex - 1] != sChars[sIndex] && pChars[pIndex - 1] != '.') && pChars[pIndex] == '*') { // 多个字符串
                        if (sIndex == 0) {
                            pIndex++;
                            continue;
                        } else {
                            return false;
                        }
                    }
                    if (sIndex == 0) {
                        pIndex++;
                        continue;
                    }
                    return false;
                }
            }
        }
        if (sIndex != sChars.length) {
            return false;
        }
        if (pIndex < pChars.length) {
            if (pIndex == pChars.length - 1 && pChars[pChars.length - 1] == '*') {
                return true;
            }
            if (pIndex == pChars.length - 2 && pChars[pChars.length - 1] == '*') {
                return true;
            }
            if (pChars[pIndex] == '*') {
                char[] lastOne = new char[]{sChars[sChars.length - 1]};
                return charMatch(lastOne, subString(pIndex + 1, pChars));
            }
            // 1. 剩下的string，去除开头的符号；
            // 2. 剩下的多东西必须是'';否则返回false;
            char[] remainChars = subString(pIndex, pChars);
            if (remainChars[0] == '.' || remainChars[0] == '*') {
                remainChars = subString(1, remainChars);
            }
            if (charMatch(new char[0], remainChars)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static char[] subString(int start, int end, char[] arr) {
        int length = end - start + 1;
        char[] resultArr = new char[length];
        for (int i = start; i <= end; i++) {
            resultArr[i - start] = arr[i];
        }
        return resultArr;
    }

    public static char[] subString(int start, char[] arr) {

        int end = arr.length - 1;
        return subString(start, end, arr);
    }
}
