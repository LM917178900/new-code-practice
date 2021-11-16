package com.leinovo.codepractice.hub1_20;

import java.util.regex.Pattern;

/**
 * 请实现一个函数用来判断字符串str是否表示数值（包括科学计数法的数字，小数和整数）。
 * <p>
 * 科学计数法的数字(按顺序）可以分成以下几个部分:
 * 1.若干空格
 * 2.一个整数或者小数
 * 3.（可选）一个 'e' 或 'E' ，后面跟着一个整数(可正可负)
 * 4.若干空格
 * <p>
 * 小数（按顺序）可以分成以下几个部分：
 * 1.若干空格
 * 2.（可选）一个符号字符（'+' 或 '-'）
 * 3. 可能是以下描述格式之一:
 * 3.1 至少一位数字，后面跟着一个点 '.'
 * 3.2 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 3.3 一个点 '.' ，后面跟着至少一位数字
 * 4.若干空格
 * <p>
 * 整数（按顺序）可以分成以下几个部分：
 * 1.若干空格
 * 2.（可选）一个符号字符（'+' 或 '-')
 * 3. 至少一位数字
 * 4.若干空格
 * <p>
 * 例如，字符串["+100","5e2","-123","3.1416","-1E-16"]都表示数值。
 * 但是["12e","1a3.14","1.2.3","+-5","12e+4.3"]都不是数值。
 * <p>
 * 提示:
 * 1.1 <= str.length <= 25
 * 2.str 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 * 3.如果怀疑用例是不是能表示为数值的，可以使用python的print(float(str))去查看
 * 进阶：时间复杂度，空间复杂度
 * <p>
 * 测试数据
 * e       "123.45e+6"
 * false
 *
 * @description: JZ20
 * @author: leiming5
 * @date: 2021/11/11 8:59
 */
public class JZ20 {
    public static void main(String[] args) {

        String str = "12e123e12";
        boolean numericStr = isNumericStr(str);
        System.out.println(numericStr);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 1. 开头是数字或正负号或'.'号；
     * 2. 非开头，
     * 数字前面是数字或正负号或'.'号,前是e/
     * 3. '.'号前面是数字或正负号；
     * 4. 'e' 前是数字或'.'号
     * 5. 'e'的正负号前是'E/e';
     *
     * @param str string字符串
     * @return bool布尔型
     */

    public static boolean isNumericStr(String str) {
        // write code here
        str = str.trim();

        int length = str.length();
        if (length == 0) {
            return false;
        }
        boolean[] f = new boolean[length];

        boolean firstNumber = false;
        boolean canBeDot = true;
        boolean secondNumber = false;
        boolean beEe = false;
        boolean canBeESign = true;

        if (length == 1 && (str.charAt(length - 1) == 43 || str.charAt(length - 1) == 45 || str.charAt(length - 1) == 46
                || str.charAt(length - 1) == 69 || str.charAt(length - 1) == 101)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            char currentChar = str.charAt(i);
            boolean isNumber = isNumber(currentChar);
            // 空格
            if (!firstNumber && canBeDot && str.charAt(i) == ' ') {
                if ((i + 1) < length && (str.charAt(i + 1) == ' ' || isNumber(str.charAt(i + 1)) || currentChar == 43 || currentChar == 45)) {
                    if (i == 0) {
                        f[0] = true;
                    } else {
                        f[i] = f[i - 1];
                    }
                } else {
                    break;
                }
                continue;
            }


            if (i == 0) {
                // +/-号
                if (currentChar == 43 || currentChar == 45) {
                    if (str.charAt(i + 1) == 46 && i + 1 == length - 1) {
                        break;
                    }
                    if ((i + 1) < length && (isNumber(str.charAt(i + 1)) || str.charAt(i + 1) == 46)) {
                        f[0] = true;
                        i++;
                        f[i] = f[i - 1];
                        firstNumber = true;
                    } else {
                        break;
                    }
                }
                // 数字
                if (isNumber) {
                    f[0] = true;
                    firstNumber = true;
                }

                // .号
                if (currentChar == 46) {
                    if ((i + 1) < length && isNumber(str.charAt(i + 1))) {
                        f[0] = true;
                        canBeDot = false;
                        i++;
                        f[i] = f[i - 1];
                        secondNumber = true;
                    } else {
                        break;
                    }
                }

                continue;
            }

            // 数字
            if (isNumber) {
                f[i] = f[i - 1];
                continue;
            }

            // .号
            if (canBeESign && canBeDot && firstNumber && str.charAt(i) == 46) {
                if (i + 1 == length) {
                    f[i] = isNumber(str.charAt(i - 1));
                }
                if ((i + 1) < length && (isNumber(str.charAt(i + 1)))) {
                    f[i] = f[i - 1];
                    canBeDot = false;
                    i++;
                    f[i] = f[i - 1];
                    secondNumber = true;
                } else if ((i + 1) < length && (str.charAt(i + 1) == 69 || str.charAt(i + 1) == 101)) {
                    f[i] = f[i - 1];
                    canBeDot = false;
                    i++;
                    f[i] = f[i - 1];
                    secondNumber = true;
                } else {
                    break;
                }
                continue;
            }

            // e/E
            if (!beEe && (firstNumber || secondNumber) && (str.charAt(i) == 69 || str.charAt(i) == 101)) {
                if (i + 1 == length) {
                    f[i] = false;
                    break;
                }

                f[i] = f[i - 1];
                beEe = true;
                continue;
            }

            // e 后面的 +/- 号
            if (beEe && (currentChar == 43 || currentChar == 45)) {
                if ((i + 1) < length && isNumber(str.charAt(i + 1))) {
                    f[i] = f[i - 1];
                    canBeESign = false;
                    i++;
                    f[i] = f[i - 1];
                } else {
                    break;
                }
                continue;
            }

            if (beEe && isNumber) {
                f[i] = f[i - 1];
                continue;
            }
        }

        return f[length - 1];
    }

    private static boolean isNumber(char currentChar) {

        return 48 <= currentChar && currentChar <= 57;
    }

    public boolean isNumeric1(String str) {
        int n = str.length();
        int index = 0;
        boolean hasNum = false, hasE = false;
        boolean hasSign = false, hasDot = false;
        while (index < n && str.charAt(index) == ' ') {
            index++;
        }
        while (index < n) {
            while (index < n && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                index++;
                hasNum = true;
            }
            if (index == n) {
                break;
            }
            char c = str.charAt(index);
            if (c == 'e' || c == 'E') {
                if (hasE || !hasNum) {
                    return false;
                }
                hasE = true;
                hasNum = false;
                hasSign = false;
                hasDot = false;
            } else if (c == '+' || c == '-') {
                if (hasSign || hasNum || hasDot) {
                    return false;
                }
                hasSign = true;
            } else if (c == '.') {
                if (hasDot || hasE) {
                    return false;
                }
                hasDot = true;
            } else if (c == ' ') {
                break;
            } else {
                return false;
            }
            index++;
        }
        while (index < n && str.charAt(index) == ' ') {
            index++;
        }
        return hasNum && index == n;
    }

    /**
     *  自己写的正则
     *
     * @param str
     * @return
     */
    public boolean isNumeric3(String str) {
        String p = "^\\s*[+-]?(\\d*\\.?\\d+|\\d+\\.?\\d*)(?:[eE][+-]?\\d+)?\\s*$";
        return Pattern.matches(p,str);
    }

    public boolean isNumeric2(String str) {
        // write code here
        //        ^表示开头 $ 表示结尾  java中两个\\ 代表一个\
        //        * 零次或多次匹配前面的字符或子表达式
        //        ？零次或一次匹配前面的字符或子表达式
        //        + 一次或多次匹配前面的字符或子表达式
        //        [] 字符集。匹配包含的任一字符
        //        (:? )匹配 pattern 但不捕获该匹配的子表达式，即它是一个非捕获匹配
        /**
         *  1. ^ 表示匹配开始，$表示匹配结束
         *  2. [+-]? 表示0个或一个其中之一；
         *  3. \d* 表示多个0个或多个数字；
         *  4. \. 表示 '.'
         *  5. \d+ 表示一个或多个数字
         *  6. | 或者后面的模板
         *  7. \d+ 表示一个或多个数字
         *  8. (\.\d*)? 表示 小数点和后面的数组，？表示可能没有小数
         *  9. [eE][+-]?\d+ e/E 之一 然后可能有正负号，后面需要跟一个或者多个数字
         */
        str = str.trim();
        String p = "^[+-]?(\\d*\\.\\d+|\\d+(\\.\\d*)?)(?:[eE][+-]?\\d+)?$";
        return Pattern.matches(p, str);
    }

}
